package routing

import gson
import interactor.user.UserLoader
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import jwt.JwtConfig
import request.CredentialsRequest
import session.Session

fun Routing.authRouting() {
    post("/auth") {
        val body = call.receive<String>()
        val credentials = gson.fromJson(body, CredentialsRequest::class.java)
        val identified = UserLoader.identify(credentials.name, credentials.password)
        if (identified != null) {
            call.sessions.set(Session("jwt", JwtConfig.makeToken(user = identified)))
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
}