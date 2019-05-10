package routing

import claim
import gson
import interactor.artist.ArtistLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import jwtPayload

fun Routing.artistRouting() {
    get("/artist/{id}") {
        val artistId = call.parameters["id"]!!
        try {
            val artist = ArtistLoader.artist(artistId)
            call.respond(gson.toJson(artist))
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, ex.message!!)
        }
    }

    authenticate {
        post("/artist/{id}/follow") {
            val artistId = call.parameters["id"]!!
            try {
                ArtistLoader.follow(
                    call.jwtPayload()!!.claim("artist_id"),
                    artistId
                )
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ex.message!!)
            }
        }

        post("/artist/{id}/unfollow") {
            val artistId = call.parameters["id"]!!
            try {
                ArtistLoader.unfollow(
                    call.jwtPayload()!!.claim("artist_id"),
                    artistId
                )
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ex.message!!)
            }
        }
    }
}