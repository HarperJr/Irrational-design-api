package routing

import FileManager
import arg
import authPayload
import database.document.Post
import gson
import interactor.post.PostLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.*
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.litote.kmongo.toId
import read
import request.PostRequest
import utils.ApiException
import java.util.*

fun Routing.postRouting() {
    get("/post/{id}") {
        val postId = call.parameters["id"]!!
        val artistId = call.runCatching { authPayload().artistId }.getOrNull()
        val post = PostLoader.post(artistId, postId)
        call.respond(gson.toJson(post))
    }

    get("/posts/{filter}") {
        val filter = call.parameters["filter"]!!
        val from = call.arg<Int>("from") ?: 0
        val to = call.arg<Int>("to") ?: 20
        call.respond(PostLoader.posts(from, to, filter))

    }

    get("/categories") {
        call.respond(PostLoader.categories())
    }

    authenticate {
        get("post/{id}/liked") {
            val postId = call.parameters["id"]!!
            call.respond(
                PostLoader.liked(
                    call.authPayload().artistId,
                    postId
                )
            )
        }

        post("/upload") {
            val multipart = call.receiveMultipart()
            with(multipart.readAllParts()) {
                val postBody = find { body -> body.name.equals("post-part") }
                    ?: throw ApiException(HttpStatusCode.BadRequest, "No post part provided")
                val post = gson.fromJson(postBody.read(), PostRequest::class.java)
                val rawImages = filter { body -> body.name?.startsWith("image") ?: false }
                    .map { body ->
                        if (body is PartData.FileItem) {
                            val bytes = body.streamProvider().use { inputStream ->
                                base64Decoder.decode(inputStream.readBytes())
                            }
                            Image(body.originalFileName!!, bytes)
                        } else throw ApiException(HttpStatusCode.BadRequest, "Image file is invalid")
                    }

                if (rawImages.isEmpty()) {
                    throw ApiException(
                        HttpStatusCode.BadRequest,
                        "No image parts provided, at least one is required"
                    )
                }

                PostLoader.upload(
                    Post(
                        artistId = call.authPayload().artistId.toId(),
                        title = post.title,
                        subtitle = post.subtitle,
                        description = post.description,
                        date = Date().time
                    ),
                    categories = post.categories,
                    tags = post.tags,
                    images = rawImages
                )
            }
            call.respond(HttpStatusCode.OK, "Post successfully added")
        }

        post("post/{id}/like") {
            val postId = call.parameters["id"]!!
            val initial = call.arg<Boolean>("initial")
                ?: throw ApiException(HttpStatusCode.BadRequest, "Argument <initial: Boolean> is required")
            PostLoader.like(postId, call.authPayload().artistId, initial)
            call.respond(HttpStatusCode.OK)
        }

        post("post/{id}/bookmark") {
            val postId = call.parameters["id"]!!
            val initial = call.arg<Boolean>("initial")
                ?: throw ApiException(HttpStatusCode.BadRequest, "Argument <initial: Boolean> is required")
            PostLoader.bookmark(postId, call.authPayload().artistId, initial)
            call.respond(HttpStatusCode.OK)
        }
    }

    static(ARTS) {
        files(FileManager.artsFolder())
    }
}

typealias Image = Pair<String, ByteArray>

private val base64Decoder = Base64.getDecoder()
private const val ARTS = "arts"