package routing

import gson
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import request.CommentRequest

fun Routing.commentRouting() {
    /*filter*/
    get("/comments") {
        val postId = call.request.queryParameters["post_id"]
        if (postId != null) {
            val comments = interactor.comment.CommentLoader.comments(postId)
            call.respond(gson.toJson(comments))
        } else {
            call.respond(io.ktor.http.HttpStatusCode.NotFound)
        }
    }

    post("/comment") {
        val request = call.receive<String>()
        val comment = gson.fromJson<CommentRequest>(request, request.CommentRequest::class.java)
        interactor.comment.CommentLoader.comment(
            postId = comment.postId,
            author = comment.author,
            content = comment.content
        )
    }
}