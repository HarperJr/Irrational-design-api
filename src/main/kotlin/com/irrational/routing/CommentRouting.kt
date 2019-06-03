package com.irrational.routing

import com.irrational.authPayload
import com.irrational.interactor.comment.CommentLoader
import com.irrational.request.CommentRequest
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

fun Routing.comments() {
    get("/post/{id}/comments") {
        val postId = call.parameters["id"]!!
        call.respond(CommentLoader.comments(postId))
    }

    authenticate {
        post("/post/{id}/comment") {
            val postId = call.parameters["id"]!!
            val commentRequest = call.receive<CommentRequest>()
            val commentResponse = CommentLoader.insert(
                postId = postId,
                artistId = call.authPayload().artistId,
                content = commentRequest.content
            )
            call.respond(commentResponse)
        }
    }
}