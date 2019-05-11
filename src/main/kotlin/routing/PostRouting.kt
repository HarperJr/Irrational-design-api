package routing

import arg
import claim
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
import jwtPayload
import org.litote.kmongo.toId
import read
import readBytes
import request.PostRequest
import java.util.*

fun Routing.postRouting() {
    get("/post/{id}") {
        val postId = call.parameters["id"]!!

        try {
            val post = PostLoader.post(call.jwtPayload()?.claim("artistId"), postId)
            call.respond(gson.toJson(post))
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, ex.message!!)
        }
    }

    get("/posts/{filter}") {
        val filter = call.parameters["filter"]!!
        val from = call.arg<Int>("from") ?: 0
        val to = call.arg<Int>("to") ?: 0
        try {
            val posts = PostLoader.posts(from, to, filter)
            call.respond(gson.toJson(posts))
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, ex.message!!)
        }
    }

    authenticate {
        post("/upload") {
            try {
                val multipart = call.receiveMultipart()
                with(multipart.readAllParts()) {
                    val postBody = find { it.contentType == ContentType.Application.Json }
                        ?: throw Exception("Unable to handle multipart")
                    val post = gson.fromJson(postBody.read(), PostRequest::class.java)

                    val rawImages = filter {
                        it.contentType == ContentType.Image.JPEG ||
                                it.contentType == ContentType.Image.PNG
                    }.map { it.readBytes() }
                    if (rawImages.isEmpty()) throw Exception("Unable to handle multipart with no images")

                    PostLoader.upload(
                        Post(
                            artistId = call.jwtPayload()!!.claim<String>("artistId").toId(),
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
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ex.message!!)
            }
        }

        post("/like/{id}") {
            val postId = call.parameters["id"]!!
            try {
                val initial = call.arg<Boolean>("initial") ?: throw Exception("Invalid arguments")
                PostLoader.like(postId, call.jwtPayload()!!.claim("artistId"), initial)
                call.respond(HttpStatusCode.OK)
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ex.message!!)
            }
        }

        post("/bookmark/{id}") {
            val postId = call.parameters["id"]!!
            try {
                val initial = call.arg<Boolean>("initial") ?: throw Exception("Invalid arguments")
                PostLoader.bookmark(postId, call.jwtPayload()!!.claim("artistId"), initial)
                call.respond(HttpStatusCode.OK)
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ex.message!!)
            }
        }
    }
}