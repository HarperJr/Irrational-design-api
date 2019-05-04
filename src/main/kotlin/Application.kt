import com.auth0.jwt.interfaces.Payload
import com.google.gson.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import io.ktor.sessions.sessions
import org.litote.kmongo.Id
import org.litote.kmongo.toId
import routing.artistRouting
import routing.authRouting
import routing.commentRouting
import routing.postRouting
import session.Session
import session.initSession

fun main() {
    embeddedServer(Netty, 8080, watchPaths = listOf("IrrationalDesign"), module = Application::module).start()
}

val artsPath = "arts/"
val gson: Gson = GsonBuilder()
    .registerTypeAdapter(Id::class.java,
        JsonSerializer<Id<Any>> { id, _, _ -> JsonPrimitive(id?.toString()) })
    .registerTypeAdapter(Id::class.java,
        JsonDeserializer<Id<Any>> { id, _, _ -> id.asString.toId() })
    .create()

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Authentication) { authenticate() }
    install(Sessions) { initSession() }
    install(Routing) {
        authRouting()
        postRouting()
        artistRouting()
        commentRouting()
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> ApplicationCall.arg(arg: String): T? = try {
    request.queryParameters[arg] as T?
} catch (ex: ClassCastException) {
    null
}

fun ApplicationCall.session(): Session = sessions.get(Session.NAME) as Session

fun ApplicationCall.jwtPayload(): Payload {
    return (authentication.principal as JWTPrincipal).payload
}

inline fun <reified T> Payload.claim(name: String): T = getClaim(name).`as`(T::class.java)
