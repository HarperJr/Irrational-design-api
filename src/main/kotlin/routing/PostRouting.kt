package routing

import gson
import interactor.post.PostLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.postRouting() {
    get("/post/{id}") {
        val postId = call.parameters["id"]!!
        val post = PostLoader.post(postId)
        if (post != null) {
            call.respond(gson.toJson(post))
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
    /*unresolved*/
    authenticate {
        get("/posts/{filter}") {
            val filter = call.parameters["filter"]!!
            val from = call.request.queryParameters["from"]?.toInt() ?: 0
            val to = call.request.queryParameters["to"]?.toInt() ?: 20

            val posts = PostLoader.posts(from, to, filter)
            call.respond(gson.toJson(posts))
        }
    }
}