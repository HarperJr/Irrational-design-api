package database.document

data class Role(
    var type: RoleType
) : Document<Role>()

enum class RoleType {
    ADMIN, USER, MODERATOR;

    companion object {
        fun by(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
            ?: throw IllegalArgumentException("RoleType with ordinal $ordinal doesn't exist")
    }
}