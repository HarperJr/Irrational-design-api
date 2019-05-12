import com.auth0.jwt.interfaces.Payload
import com.google.gson.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.content.PartData
import io.ktor.http.content.streamProvider
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import org.litote.kmongo.Id
import org.litote.kmongo.toId
import routing.*
import session.initSession

fun main() {
    embeddedServer(
        Netty, 8080,
        watchPaths = listOf("IrrationalDesign"),
        module = Application::module
    ).start()
}

val gson: Gson = GsonBuilder()
    .registerTypeAdapter(Id::class.java,
        JsonSerializer<Id<Any>> { id, _, _ -> JsonPrimitive(id?.toString()) })
    .registerTypeAdapter(Id::class.java,
        JsonDeserializer<Id<Any>> { id, _, _ -> id.asString.toId() })
    .create()

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson { setPrettyPrinting() }
    }
    install(Authentication) { authenticate() }
    install(Sessions) { initSession() }
    install(Routing) {
        authRouting()
        postRouting()
        artistRouting()
        commentRouting()
        paymentRouting()
    }
}

//region extensions

@Suppress("UNCHECKED_CAST")
fun <T> ApplicationCall.arg(arg: String): T? = try {
    request.queryParameters[arg] as T?
} catch (ex: ClassCastException) {
    null
}

fun ApplicationCall.jwtPayload(): Payload? {
    return (authentication.principal as JWTPrincipal?)?.payload
}

inline fun <reified T> Payload.claim(name: String): T = getClaim(name).`as`(T::class.java)

fun PartData.readBytes() = when (this) {
    is PartData.FileItem -> streamProvider().use { it.readBytes() }
    is PartData.FormItem -> value.toByteArray()
    else -> throw Exception("Unable to read unsupported part data")
}

fun PartData.read() = String(readBytes())

//endregion
