package routing

import database.document.User
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
import request.RegisterRequest
import session.Session
import java.util.*

fun Routing.authRouting() {
    post("/auth") {
        val body = call.receive<String>()
        val credentials = gson.fromJson(body, CredentialsRequest::class.java)
        val identified = UserLoader.findByCredentials(credentials.name, credentials.password)
        if (identified != null) {
            with(call) {
                sessions.set(Session(UUID.randomUUID().toString(), JwtConfig.makeToken(user = identified)))
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
            UserLoader.insert(
                User(
                    name = form.name,
                    password = form.password,
                    email = form.email,
                    registered = Date().time
                )
            )
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.Conflict, "User with same name or email already exists")
        }
    }
}