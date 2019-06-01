package com.irrational.routing

import com.irrational.authPayload
import com.irrational.interactor.moderation.ModerationLoader
import com.irrational.utils.ApiException
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import org.litote.kmongo.toId

fun Routing.moderationRouting() {

    authenticate {
        post("moderation/push") {
            val postId = call.request.queryParameters["postId"] ?: return@post call.respond(
                ApiException(
                    statusCode = HttpStatusCode.BadRequest,
                    errorMessage = "post doesn't exist"
                )
            )
            call.respond(ModerationLoader.push_complaint(call.authPayload().artistId.toId(), postId.toId()))
        }
        post("moderation/accept") {
            val complaintId = call.request.queryParameters["complaintId"] ?: return@post call.respond(
                ApiException(
                    statusCode = HttpStatusCode.BadRequest,
                    errorMessage = "complaint doesn't exist"
                )
            )
            call.respond(
                ModerationLoader.accept_complaint(complaintId = complaintId.toId())
            )
        }

        post("moderation/reject") {
            val complaintId = call.request.queryParameters["complaintId"] ?: return@post call.respond(
                ApiException(
                    statusCode = HttpStatusCode.BadRequest,
                    errorMessage = "complaint doesn't exist"
                )
            )
            call.respond(ModerationLoader.reject_complaint(complaintId.toId()))
        }

        post("moderation/complaints"){
            call.respond(ModerationLoader.complaints())
        }
    }
}