package com.irrational.routing

import com.irrational.authPayload
import com.irrational.interactor.comment.CommentLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import com.irrational.request.CommentRequest

fun Routing.commentRouting() {
    get("/post/{id}/comments") {
        val postId = call.parameters["post_id"]!!
        call.respond(CommentLoader.comments(postId))
    }

    authenticate {
        post("post/comment") {
            val comment = call.receive<CommentRequest>()
            CommentLoader.insert(
                postId = comment.postId,
                author = call.authPayload().artistId,
                content = comment.content
            )
            //We need to respond with the new insert
            call.respond(HttpStatusCode.OK)
        }
    }
}