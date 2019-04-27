package routing

import gson
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.artistRouting() {
    /*id*/
    get("/artist/{id}") {
        val artistId = call.parameters["id"]!!
        val artist = interactor.artist.ArtistLoader.artist(artistId)
        call.respond(gson.toJson(artist))
    }
}