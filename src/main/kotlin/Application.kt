import com.google.gson.*
import interactor.post.PostLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authenticate
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.request.receive
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import org.litote.kmongo.Id
import org.litote.kmongo.toId
import request.PostRequest
import routing.*

fun main() {
    embeddedServer(Netty, 8080, watchPaths = listOf("IrrationalDesign"), module = Application::module).start()
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
    install(Authentication) { authenticate() }
    install(Sessions) { initSession() }
    install(Routing) {
        authRouting()
        postRouting()
        artistRouting()
        commentRouting()

        authenticate {
            post("/upload") {
                val request = call.receive<String>()
                val post = gson.fromJson<PostRequest>(request, PostRequest::class.java)
                PostLoader.upload(post)
            }
        }
    }
}