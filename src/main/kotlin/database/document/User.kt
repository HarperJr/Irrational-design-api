package database.document

data class User(
    var name: String,
    var password: String,
    var email: String,
    var registered: Long
) : Document<User>()