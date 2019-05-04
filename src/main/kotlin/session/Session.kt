package session

import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

fun Sessions.Configuration.initSession() {
    cookie<Session>(Session.NAME) {
        cookie.path = "/"
    }
}

data class Session(val sessionId: String, val barrier: String) {
    companion object {
        const val NAME = "SESSION"
    }
}