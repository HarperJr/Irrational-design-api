import com.auth0.jwk.JwkProviderBuilder
import com.google.gson.*
import interactor.artist.ArtistLoader
import interactor.comment.CommentLoader
import interactor.post.PostLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.litote.kmongo.Id
import org.litote.kmongo.toId
import request.CommentRequest
import request.PostRequest
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("IrrationalDesign"), module = Application::module).start()
}

val gson: Gson = GsonBuilder()
    .registerTypeAdapter(Id::class.java,
        JsonSerializer<Id<Any>> { id, _, _ -> JsonPrimitive(id?.toString()) })
    .registerTypeAdapter(Id::class.java,
        JsonDeserializer<Id<Any>> { id, _, _ -> id.asString.toId() })
    .create()

const val jwkIssuer = "http://127.0.0.1"
const val jwkRealm = "irrationaldesign"
const val jwtAudience = "jwt-audience"
val jwkProvider = JwkProviderBuilder(jwkIssuer)
    .cached(10, 24, TimeUnit.HOURS)
    .rateLimited(10, 1, TimeUnit.MINUTES)
    .build()

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Authentication) {
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
    install(Routing) {
        get("/post/{id}") {
            val postId = call.parameters["id"]!!
            val post = PostLoader.post(postId)
            if (post != null) {
                call.respond(gson.toJson(post))
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        /*unresolved*/get("/posts/{filter}") {
        val filter = call.parameters["filter"]!!
        val from = call.request.queryParameters["from"]?.toInt() ?: 0
        val to = call.request.queryParameters["to"]?.toInt() ?: 20
        val posts = PostLoader.posts(from, to, filter)
        call.respond(gson.toJson(posts))
    }
        /*id*/get("/artist/{id}") {
        val artistId = call.parameters["id"]!!
        val artist = ArtistLoader.artist(artistId)
        call.respond(gson.toJson(artist))
    }
        /*filter*/get("/comments") {
        val postId = call.request.queryParameters["post_id"]
        if (postId != null) {
            val comments = CommentLoader.comments(postId)
            call.respond(gson.toJson(comments))
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

        post("/comment") {
            val request = call.receive<String>()
            val comment = gson.fromJson<CommentRequest>(request, CommentRequest::class.java)
            CommentLoader.comment(
                postId = comment.postId,
                author = comment.author,
                content = comment.content
            )
        }

        post("/upload") {
            val request = call.receive<String>()
            val post = gson.fromJson<PostRequest>(request, PostRequest::class.java)
            PostLoader.upload(post)
        }
    }
}