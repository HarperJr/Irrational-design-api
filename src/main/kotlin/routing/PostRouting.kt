package routing

import arg
import gson
import interactor.post.PostLoader
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import param

fun Routing.postRouting() {
    get("/post/{id}") {
        val postId = call.param<String>("id")!!
        val post = PostLoader.post(postId)
        if (post != null) {
            call.respond(gson.toJson(post))
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
    /*unresolved*/
    get("/posts/{filter}") {
        val filter = call.param<String>("filter")!!
        val from = call.arg<Int>("from") ?: 0
        val to = call.arg<Int>("to") ?: 0

        val posts = PostLoader.posts(from, to, filter)
        call.respond(gson.toJson(posts))
    }
}