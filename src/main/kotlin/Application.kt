import com.google.gson.Gson
import com.google.gson.GsonBuilder
import interactor.comment.CommentLoader
import interactor.post.PostLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("IrrationalDesign"), module = Application::module).start()
}

val gson: Gson = GsonBuilder().create()

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/post/{postId}") {
            val post = PostLoader.post("")
            if (post != null) {
                call.respond(gson.toJson(post))
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/posts/{filter}") {
            val from = call.request.queryParameters["from"]?.toInt() ?: 0
            val to = call.request.queryParameters["to"]?.toInt() ?: 20

            val posts = PostLoader.posts(from, to)
            call.respond(gson.toJson(posts))
        }
        get("/artist/") {

        }

        get("/comments") {
            val postId = call.request.queryParameters["post_id"]
            if (postId != null) {
                val comments = CommentLoader.comments(postId)
                call.respond(gson.toJson(comments))
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}