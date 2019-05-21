import com.auth0.jwt.interfaces.Payload
import com.google.gson.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.features.*
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.streamProvider
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import org.litote.kmongo.Id
import org.litote.kmongo.toId
import routing.*
import session.initSession
import utils.ApiException
import java.time.Duration

fun main() {
    embeddedServer(
        Netty, 8080,
        watchPaths = listOf("IrrationalDesign"),
        module = Application::module
    ).start()
}

val gson: Gson = GsonBuilder()
    .serializeNulls()
    .setPrettyPrinting()
    .disableHtmlEscaping()
    .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .registerTypeAdapter(Id::class.java,
        JsonSerializer<Id<Any>> { id, _, _ -> JsonPrimitive(id?.toString()) })
    .registerTypeAdapter(Id::class.java,
        JsonDeserializer<Id<Any>> { id, _, _ -> id.asString.toId() })
    .create()

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter(gson))
    }
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        header(HttpHeaders.AccessControlAllowOrigin)
        header(HttpHeaders.Authorization)
        anyHost()
        allowCredentials = true
        maxAge = Duration.ofDays(1)
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
    install(StatusPages) {
        exception<ApiException> { ex ->
            call.respond(ex.statusCode, ex.errorMessage)
        }
        exception<Exception> { ex ->
            call.respond(HttpStatusCode.BadRequest, ex.message ?: "Unhandled error by passing arguments")
        }
    }
}

//region extensions

@Suppress("UNCHECKED_CAST")
fun <T> ApplicationCall.arg(arg: String): T? = try {
    request.queryParameters[arg] as T?
} catch (ex: ClassCastException) {
    null
}

fun ApplicationCall.authPayload(): AuthPayload {
    return authentication.principal?.let {
        AuthPayload((it as JWTPrincipal).payload)
    } ?: throw Exception("Payload is null")
}

inline fun <reified T> Payload.claim(name: String): T = getClaim(name).`as`(T::class.java)

fun PartData.readBytes() = when (this) {
    is PartData.FileItem -> streamProvider().use { it.readBytes() }
    is PartData.FormItem -> value.toByteArray()
    else -> throw Exception("Unable to read unsupported part data")
}

fun PartData.read() = String(readBytes())

//endregion
