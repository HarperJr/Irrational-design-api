package routing

import claim
import gson
import interactor.comment.CommentLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import jwtPayload
import request.CommentRequest

fun Routing.commentRouting() {
    /*filter*/
    get("/comments/{id}") {
        val postId = call.parameters["post_id"]!!
        try {
            val comments = CommentLoader.comments(postId)
            call.respond(gson.toJson(comments))
        } catch (ex: Exception) {

        }
    }

    authenticate {
        post("/comment") {
            val body = call.receive<String>()
            val comment = gson.fromJson<CommentRequest>(body, CommentRequest::class.java)
            CommentLoader.comment(
                postId = comment.postId,
                author = call.jwtPayload()!!.claim("artist_id"),
                content = comment.content
            )
        }
    }
}