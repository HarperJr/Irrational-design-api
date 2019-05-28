package com.irrational.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.irrational.database.document.Artist
import java.time.Duration
import java.util.*

object JwtConfig {

    private const val secret = "zAP5MBA4B4Ijz0MZaS48"
    private const val issuer = "irrational-design.cc"
    private val validityInMs = Duration.ofHours(10).toMillis() // 10 hours
    private val algorithm = Algorithm.HMAC512(secret)

    const val ARTIST_ID_CLAIM = "id"

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun makeToken(artist: Artist): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim(ARTIST_ID_CLAIM, artist.id.toString())
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}