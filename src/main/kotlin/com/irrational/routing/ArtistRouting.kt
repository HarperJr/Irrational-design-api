package com.irrational.routing

import com.irrational.FileManager
import com.irrational.authPayload
import com.irrational.interactor.artist.ArtistLoader
import com.irrational.utils.ApiException
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

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
            val initial = call.request.queryParameters["initial"]?.toBoolean()
                ?: throw ApiException(
                    HttpStatusCode.BadRequest,
                    "Argument <initial: Boolean> is required"
                )
            ArtistLoader.follow(
                call.authPayload().artistId,
                artistId,
                initial
            )
            call.respond(HttpStatusCode.OK, "Followed")
        }
    }

    static(AVATARS) {
        files(FileManager.avatarsFolder())
    }
}

private const val AVATARS = "avatars"