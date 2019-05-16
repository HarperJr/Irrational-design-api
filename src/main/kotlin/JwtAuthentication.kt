import com.auth0.jwt.interfaces.Payload
import interactor.artist.ArtistLoader
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import jwt.JwtConfig


fun Authentication.Configuration.authenticate() {
    jwt {
        verifier(JwtConfig.verifier)
        realm = "ktor.io"
        validate { credentials ->
            val authorized = ArtistLoader.find(credentials.payload.claim("artistId"))
            authorized?.let { JWTPrincipal(credentials.payload) }
        }
    }
}

data class AuthPayload(private val jwtPayload: Payload) {
    val artistId: String = jwtPayload.claim("artistId")
}