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
        call.respond(ArtistLoader.artist(artistId))
    }

    get("/artist/{id}/followers") {
        val artistId = call.parameters["id"]!!
        call.respond(ArtistLoader.followers(artistId))
    }

    get("/artist/{id}/follows") {
        val artistId = call.parameters["id"]!!
        call.respond(ArtistLoader.follows(artistId))
    }

    authenticate {
        get("artist/{id}/followed") {
            val artistId = call.parameters["id"]!!
            call.respond(
                ArtistLoader.followed(
                    call.authPayload().artistId,
                    artistId
                )
            )
        }

        post("/artist/{id}/follow") {
            val artistId = call.parameters["id"]!!
            val initial = call.arg<Boolean>("initial")
                ?: throw ApiException(HttpStatusCode.BadRequest, "Argument <initial: Boolean> is required")
            ArtistLoader.follow(
                call.authPayload().artistId,
                artistId,
                initial
            )
            call.respond(HttpStatusCode.OK, "Followed")
        }
    }
}