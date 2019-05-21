package routing

import gson
import interactor.artist.ArtistLoader
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import jwt.JwtConfig
import request.AuthRequest
import request.RegisterRequest

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
        val body = call.receive<String>()
        val form = gson.fromJson(body, RegisterRequest::class.java)
        ArtistLoader.insert(form.name, form.password, form.email)
        call.respond(HttpStatusCode.OK, "Successfully registered")
    }
}

data class TokenResponse(val jwtToken: String)