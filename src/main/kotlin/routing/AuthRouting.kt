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
import request.CredentialsRequest
import request.RegisterRequest

fun Routing.authRouting() {
    post("/auth") {
        val body = call.receive<String>()
        val credentials = gson.fromJson(body, CredentialsRequest::class.java)
        val identified = ArtistLoader.findByCredentials(credentials.name, credentials.password)
        if (identified != null) {
            with(call) {
                call.respond(JwtConfig.makeToken(identified))
                respond(HttpStatusCode.OK)
            }
        } else {
            call.respond(HttpStatusCode.Forbidden, "Wrong name or password")
        }
    }

    post("/register") {
        val body = call.receive<String>()
        val form = gson.fromJson(body, RegisterRequest::class.java)
        try {
            ArtistLoader.insert(form.name, form.password, form.email)
            call.respond(HttpStatusCode.OK, "Successfully registered")
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.Conflict, "User with same name or email already exists")
        }
    }
}