package routing

import FileManager
import arg
import claim
import database.document.Art
import database.document.Post
import gson
import interactor.art.ArtLoader
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
import org.litote.kmongo.toId
import request.PostRequest
import java.nio.file.Paths
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
            val multipart = call.receiveMultipart()
            val arts = mutableListOf<Pair<String, String>>()
            var postId = ""
            multipart.forEachPart { part ->
                if (part is PartData.FormItem) {
                    val content = part.value
                    when (part.contentType) {
                        ContentType.Application.Json -> {
                            val postRequest = gson.fromJson(content, PostRequest::class.java)
                            postId = PostLoader.insert(
                                Post(
                                    artistId = call.jwtPayload()!!.claim<String>("artistId").toId(),
                                    title = postRequest.title,
                                    subtitle = postRequest.subtitle,
                                    description = postRequest.description,
                                    date = Date().time
                                ), postRequest.categories, postRequest.tags
                            )
                        }
                        ContentType.Image.PNG,
                        ContentType.Image.JPEG -> {
                            val artName = part.name!!
                            FileManager.save(Paths.get("/arts").resolve(artName), content.toByteArray())
                                .also { path -> arts.add(Pair(artName, path)) }
                        }
                    }
                }
            }
            ArtLoader.insert(arts.map {
                Art(
                    postId = postId,
                    name = it.first,
                    link = it.second
                )
            })
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