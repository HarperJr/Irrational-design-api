package session

import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

fun Sessions.Configuration.initSession() {
    cookie<Session>("ID_COOKIE") {
        cookie.path = "/"
    }
}

data class Session(val name: String, val value: String)