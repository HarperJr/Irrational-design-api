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
        post("/complaint/{id}/push") {
            if (!ModerationLoader.moderatorCheck(call.authPayload().roleId.toId())) call.respond(
                ApiException(
                    statusCode = HttpStatusCode.Forbidden,
                    errorMessage = "Access denied"
                )
            )
            val postId = call.parameters["id"] ?: return@post call.respond(
                ApiException(
                    statusCode = HttpStatusCode.BadRequest,
                    errorMessage = "post doesn't exist"
                )
            )
            call.respond(ModerationLoader.pushComplaint(call.authPayload().artistId.toId(), postId.toId()))
        }
        post("/complaint/{id}/accept") {
            if (!ModerationLoader.moderatorCheck(call.authPayload().roleId.toId())) call.respond(
                ApiException(
                    statusCode = HttpStatusCode.Forbidden,
                    errorMessage = "Access denied"
                )
            )
            val complaintId = call.parameters["id"] ?: return@post call.respond(
                ApiException(
                    statusCode = HttpStatusCode.BadRequest,
                    errorMessage = "complaint doesn't exist"
                )
            )
            call.respond(
                ModerationLoader.acceptComplaint(complaintId = complaintId.toId())
            )
        }

        post("/complaint/{id}/reject") {
            if (!ModerationLoader.moderatorCheck(call.authPayload().roleId.toId())) call.respond(
                ApiException(
                    statusCode = HttpStatusCode.Forbidden,
                    errorMessage = "Access denied"
                )
            )
            val complaintId = call.parameters["id"] ?: return@post call.respond(
                ApiException(
                    statusCode = HttpStatusCode.BadRequest,
                    errorMessage = "complaint doesn't exist"
                )
            )
            call.respond(ModerationLoader.rejectComplaint(complaintId.toId()))
        }

        post("/complaints") {
            if (!ModerationLoader.moderatorCheck(call.authPayload().roleId.toId())) call.respond(
                ApiException(
                    statusCode = HttpStatusCode.Forbidden,
                    errorMessage = "Access denied"
                )
            )
            call.respond(ModerationLoader.complaints())
        }

        post("/post/{id}/ban") {

            val initial = call.request.queryParameters["initial"]?.toBoolean()
                ?: throw ApiException(
                    HttpStatusCode.BadRequest,
                    "Argument <initial: Boolean> is required"
                )
            ModerationLoader.blockPost(call.parameters["id"]!!.toId(), initial)
            call.respond(HttpStatusCode.OK)
        }

        post("moderation/check") {
            call.respond(ModerationLoader.isModerator(call.authPayload().roleId.toId()))
        }
    }
}