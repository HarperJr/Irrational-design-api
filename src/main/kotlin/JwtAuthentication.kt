import interactor.user.UserLoader
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import jwt.JwtConfig


fun Authentication.Configuration.authenticate() {
    jwt {
        verifier(JwtConfig.verifier)
        realm = "ktor.io"
        validate { credentials ->
            val authorized = UserLoader.find(credentials.payload.getClaim("id").asString())
            authorized?.let { JWTPrincipal(credentials.payload) }
        }
    }
}