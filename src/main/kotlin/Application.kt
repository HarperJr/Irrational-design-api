import com.google.gson.Gson
import com.google.gson.GsonBuilder
import di.AppComponent
import di.DaggerAppComponent
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
val component: AppComponent = DaggerAppComponent.create()

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/post/{postId}") {
            val post = component.postInteractor()
                .post("")
            if (post != null) {
                call.respond(gson.toJson(post))
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/posts/{filter}") {

        }
        get("/artist/{id}") {

        }
    }
}