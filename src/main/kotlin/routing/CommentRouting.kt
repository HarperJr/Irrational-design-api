package routing

import authPayload
import interactor.comment.CommentLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import request.CommentRequest
import utils.ApiException

fun Routing.commentRouting() {
    get("/post/{id}/comments") {
        val postId = call.parameters["post_id"]!!
        try {
            call.respond(CommentLoader.comments(postId))
        } catch (ex: ApiException) {
            call.respond(ex.statusCode, ex.errorMessage)
        }
    }

    authenticate {
        post("post/comment") {
            val comment = call.receive<CommentRequest>()
            try {
                CommentLoader.insert(
                    postId = comment.postId,
                    author = call.authPayload().artistId,
                    content = comment.content
                )
                //We need to respond with the new insert
                call.respond(HttpStatusCode.OK)
            } catch (ex: ApiException) {
                call.respond(ex.statusCode, ex.errorMessage)
            }
        }
    }
}