package com.irrational.routing

import com.irrational.*
import com.irrational.interactor.artist.ArtistLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import com.irrational.jwt.JwtConfig
import com.irrational.request.AuthRequest
import com.irrational.request.RegisterRequest
import com.irrational.utils.ApiException
import io.ktor.http.content.PartData
import io.ktor.http.content.readAllParts
import io.ktor.request.receiveMultipart
import io.ktor.routing.get

fun Routing.authRouting() {
    post("/auth") {
        val credentials = call.receive<AuthRequest>()
        val identified = ArtistLoader.findByCredentials(credentials.name, credentials.password)
        if (identified != null) {
            with(call) {
                call.respond(TokenResponse(JwtConfig.makeToken(identified)))
                respond(HttpStatusCode.OK)
            }
        } else {
            call.respond(HttpStatusCode.Forbidden, "Wrong name or password")
        }
    }

    post("/register") {
        val multipart = call.receiveMultipart()
        with(multipart.readAllParts()) {
            val regBody = find { body -> body.name?.equals("reg-part") ?: false }
                ?: throw ApiException(HttpStatusCode.BadRequest, "No reg part provided")
            val avatarFile = find { body -> body.name?.equals("avatar") ?: false }

            val reg = gson.fromJson(regBody.read(), RegisterRequest::class.java)
            val avatar = if (avatarFile is PartData.FileItem) {
                val bytes = avatarFile.readBytes()
                kotlin.runCatching { Base64Decoder.decodeImage(bytes) }
                    .getOrNull() ?: ImageFile(avatarFile.uuidFileName(), bytes)
            } else null
            ArtistLoader.insert(reg.name, reg.password, reg.email, avatar)
            call.respond("Successfully registered")
        }
    }

    authenticate {
        get("/credentials") {
            val artistId = call.authPayload().artistId
            call.respond(ArtistLoader.artist(artistId))
        }

        post("/change-pass") {

        }
    }
}

data class TokenResponse(val jwtToken: String)