package session

import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.header

fun Sessions.Configuration.initSession() {
    cookie<Session>(Session.NAME) {
        cookie.path = "/"
    }
}

data class Session(val value: String) {
    companion object {
        const val NAME = "Session"
    }
}

data class TokenHeader(val token: String) {
    companion object {
        const val NAME = "Authentication"
    }
}