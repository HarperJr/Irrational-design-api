package routing

import FileManager
import arg
import artsPath
import claim
import gson
import interactor.post.PostLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import jwtPayload
import request.PostRequest
import java.nio.file.Paths

fun Routing.postRouting() {
    get("/post/{id}") {
        val postId = call.parameters["id"]!!
        val post = PostLoader.post(postId)
        if (post != null) {
            call.respond(gson.toJson(post))
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    get("/posts/{filter}") {
        val filter = call.parameters["filter"]!!
        val from = call.arg<Int>("from") ?: 0
        val to = call.arg<Int>("to") ?: 0

        val posts = PostLoader.posts(from, to, filter)
        call.respond(gson.toJson(posts))
    }

    /**
     * Not completed
     */
    authenticate {
        post("/upload") {
            val multipart = call.receiveMultipart()
            val arts = mutableListOf<String>()
            multipart.forEachPart { part ->
                if (part is PartData.FormItem) {
                    val content = part.value
                    when (part.contentType) {
                        ContentType.Application.Json -> {
                            val postRequest = gson.fromJson(content, PostRequest::class.java)
                            PostLoader.insert(
                                call.jwtPayload().claim("artistId"),
                                postRequest.title,
                                postRequest.subtitle,
                                postRequest.description,
                                postRequest.categories,
                                postRequest.tags
                            )
                        }
                        ContentType.Image.PNG,
                        ContentType.Image.JPEG -> {
                            val art = part.name!!
                            FileManager.save(Paths.get(artsPath).resolve(art), content.toByteArray())
                                .also { arts.add(art) }
                        }
                    }
                }
            }
        }
    }
}