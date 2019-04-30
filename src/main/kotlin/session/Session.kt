package session

import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import java.time.Duration
import java.time.temporal.TemporalAmount

fun Sessions.Configuration.initSession() {
    cookie<Session>("SESSION") {
        cookie.path = "/"
    }
}

data class Session(val sessionId: String, val barrier: String)