import com.auth0.jwk.JwkProviderBuilder
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import java.util.concurrent.TimeUnit

const val jwkIssuer = "http://127.0.0.1"
const val jwkRealm = "irrationaldesign"
const val jwtAudience = "jwt-audience"
val jwkProvider = JwkProviderBuilder(jwkIssuer)
    .cached(10, 24, TimeUnit.HOURS)
    .rateLimited(10, 1, TimeUnit.MINUTES)
    .build()

fun Authentication.Configuration.authenticate() {
    jwt {
        verifier(jwkProvider, jwkIssuer)
        realm = jwkRealm
        validate { credentials ->
            if (credentials.payload.audience.contains(jwtAudience)) {
                JWTPrincipal(credentials.payload)
            } else null
        }
    }
}