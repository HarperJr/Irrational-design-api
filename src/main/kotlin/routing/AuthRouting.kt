package routing

import com.google.gson.JsonObject
import gson
import interactor.user.UserLoader
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.routing.Routing
import io.ktor.routing.post
import request.CredentialsRequest

fun Routing.authRouting() {
    post("/auth") {
        val credentials = gson.fromJson(call.receive<JsonObject>(), CredentialsRequest::class.java)
        if (UserLoader.validate(credentials.name, credentials.password)) {

        }
    }
}