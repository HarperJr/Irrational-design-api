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
        try {
            val artistId = call.parameters["id"]!!
            call.respond(ArtistLoader.artist(artistId))
        } catch (ex: ApiException) {
            call.respond(ex.statusCode, ex.errorMessage)
        }
    }

    get("/artist/{id}/followers") {
        try {
            val artistId = call.parameters["id"]!!
            call.respond(ArtistLoader.followers(artistId))
        } catch (ex: ApiException) {
            call.respond(ex.statusCode, ex.errorMessage)
        }
    }

    get("/artist/{id}/follows") {
        try {
            val artistId = call.parameters["id"]!!
            call.respond(ArtistLoader.follows(artistId))
        } catch (ex: ApiException) {
            call.respond(ex.statusCode, ex.errorMessage)
        }
    }

    authenticate {
        get("artist/{id}/followed") {
            try {
                val artistId = call.parameters["id"]!!
                call.respond(
                    ArtistLoader.followed(
                        call.authPayload().artistId,
                        artistId
                    )
                )
            } catch (ex: ApiException) {
                call.respond(ex.statusCode, ex.errorMessage)
            }
        }

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