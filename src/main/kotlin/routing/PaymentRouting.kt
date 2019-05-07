package routing

import api.YandexMoneyApi
import arg
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.paymentRouting() {
    post("/payment/auth") {
        call.arg<String>("redirect_uri")?.let { redirectUri ->
            YandexMoneyApi.authorize(redirectUri)
        } ?: call.respond(HttpStatusCode.BadRequest, "Redirect URI isn't provided")
    }
}