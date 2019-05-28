package com.irrational

import com.auth0.jwt.interfaces.Payload
import com.irrational.interactor.artist.ArtistLoader
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import com.irrational.jwt.JwtConfig


fun Authentication.Configuration.authenticate() {
    jwt {
        verifier(JwtConfig.verifier)
        realm = "ktor.io"
        validate { credentials ->
            val authPayload = AuthPayload(credentials.payload)
            val authorized = ArtistLoader.find(authPayload.artistId)
            authorized?.let { JWTPrincipal(credentials.payload) }
        }
    }
}

data class AuthPayload(private val jwtPayload: Payload) {
    val artistId: String = jwtPayload.claim(JwtConfig.ARTIST_ID_CLAIM)
}