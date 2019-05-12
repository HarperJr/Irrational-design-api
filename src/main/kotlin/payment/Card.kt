package payment

data class Card(
    var id: Long,
    /**
     * ???
     */
    var panFragment: String,
    /**
     * Тип ???
     */
    var type: String
)