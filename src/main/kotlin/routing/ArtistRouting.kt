package routing

import arg
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
            call.respond(ArtistLoader.artist(artistId))
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, ex.message!!)
        }
    }

    authenticate {
        post("/artist/{id}/follow") {
            val artistId = call.parameters["id"]!!
            try {
                val initial = call.arg<Boolean>("initial") ?: throw Exception("Invalid arguments")
                ArtistLoader.follow(
                    call.jwtPayload()!!.claim("artistId"),
                    artistId,
                    initial
                )
                call.respond(HttpStatusCode.OK, "Followed")
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ex.message!!)
            }
        }
    }
}