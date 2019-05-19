package routing

import arg
import authPayload
import database.document.Post
import gson
import interactor.post.PostLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.readAllParts
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.litote.kmongo.toId
import read
import readBytes
import request.PostRequest
import utils.ApiException
import java.util.*

fun Routing.postRouting() {
    get("/post/{id}") {
        val postId = call.parameters["id"]!!
        try {
            val post = PostLoader.post(call.authPayload().artistId, postId)
            call.respond(gson.toJson(post))
        } catch (ex: ApiException) {
            call.respond(ex.statusCode, ex.errorMessage)
        }
    }

    get("/posts/{filter}") {
        val filter = call.parameters["filter"]!!
        val from = call.arg<Int>("from") ?: 0
        val to = call.arg<Int>("to") ?: 20
        try {
            call.respond(PostLoader.posts(from, to, filter))
        } catch (ex: ApiException) {
            call.respond(ex.statusCode, ex.errorMessage)
        }
    }

    authenticate {
        post("/upload") {
            try {
                val multipart = call.receiveMultipart()
                with(multipart.readAllParts()) {
                    val postBody = find { it.contentType == ContentType.Application.Json }
                        ?: throw ApiException(HttpStatusCode.BadRequest, "No post json provided")
                    val post = gson.fromJson(postBody.read(), PostRequest::class.java)

                    val rawImages = filter {
                        it.contentType == ContentType.Image.JPEG ||
                                it.contentType == ContentType.Image.PNG
                    }.map { it.readBytes() }
                    if (rawImages.isEmpty()) throw ApiException(HttpStatusCode.BadRequest, "Now images provided")

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
            } catch (ex: ApiException) {
                call.respond(ex.statusCode, ex.errorMessage)
            }
        }

        post("/like/{id}") {
            val postId = call.parameters["id"]!!
            try {
                val initial = call.arg<Boolean>("initial")
                    ?: throw ApiException(HttpStatusCode.BadRequest, "Argument <initial: Boolean> is required")
                PostLoader.like(postId, call.authPayload().artistId, initial)
                call.respond(HttpStatusCode.OK)
            } catch (ex: ApiException) {
                call.respond(ex.statusCode, ex.errorMessage)
            }
        }

        post("/bookmark/{id}") {
            val postId = call.parameters["id"]!!
            try {
                val initial = call.arg<Boolean>("initial")
                    ?: throw ApiException(HttpStatusCode.BadRequest, "Argument <initial: Boolean> is required")
                PostLoader.bookmark(postId, call.authPayload().artistId, initial)
                call.respond(HttpStatusCode.OK)
            } catch (ex: ApiException) {
                call.respond(ex.statusCode, ex.errorMessage)
            }
        }
    }
}