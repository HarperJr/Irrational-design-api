package database.document

data class User(
    val name: String,
    val password: String,
    val email: String,
    val registered: Long
) : Document<User>()