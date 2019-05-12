package payment

data class Card(
    var id: Long,
    var pan_fragment: String,
    var type: String
)