import org.mindrot.jbcrypt.BCrypt

object PwdEncryptor {
    fun check(attempt: String, password: String): Boolean = BCrypt.checkpw(attempt, password)

    fun hash(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())
}