package routing

import arg
import authPayload
import interactor.artist.ArtistLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import utils.ApiException

fun Routing.artistRouting() {
    get("/artist/{id}") {
        val artistId = call.parameters["id"]!!
        try {
            call.respond(ArtistLoader.artist(artistId))
        } catch (ex: ApiException) {
            call.respond(ex.statusCode, ex.errorMessage)
        }
    }

    authenticate {
        post("/artist/{id}/follow") {
            val artistId = call.parameters["id"]!!
            try {
                val initial = call.arg<Boolean>("initial")
                    ?: throw ApiException(HttpStatusCode.BadRequest, "Argument <initial: Boolean> is required")
                ArtistLoader.follow(
                    call.authPayload().artistId,
                    artistId,
                    initial
                )
                call.respond(HttpStatusCode.OK, "Followed")
            } catch (ex: ApiException) {
                call.respond(ex.statusCode, ex.errorMessage)
            }
        }
    }
}