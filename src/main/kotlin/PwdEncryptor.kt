import org.mindrot.jbcrypt.BCrypt

object PwdEncryptor {
    fun check(attempt: String, password: String) = BCrypt.checkpw(attempt, password)

    fun encrypt(password: String) = BCrypt.hashpw(password, BCrypt.gensalt())
}