package routing

import claim
import gson
import interactor.comment.CommentLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import jwtPayload
import request.CommentRequest

fun Routing.commentRouting() {
    get("/post/{id}/comments") {
        val postId = call.parameters["post_id"]!!
        try {
            call.respond(CommentLoader.comments(postId))
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, ex.message!!)
        }
    }

    authenticate {
        post("post/comment") {
            val comment = call.receive<CommentRequest>()
            try {
                CommentLoader.comment(
                    postId = comment.postId,
                    author = call.jwtPayload()!!.claim("artistId"),
                    content = comment.content
                )
                //We need to respond with the new comment
                call.respond(HttpStatusCode.OK)
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ex.message!!)
            }
        }
    }
}