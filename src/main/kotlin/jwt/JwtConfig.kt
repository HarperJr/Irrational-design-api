package jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import database.document.User
import java.time.Duration
import java.util.*

object JwtConfig {

    private const val secret = "zAP5MBA4B4Ijz0MZaS48"
    private const val issuer = "irrational-design.cc"
    private val validityInMs = Duration.ofHours(10).toMillis() // 10 hours
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun makeToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.id.toString())
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

}