package request

data class PaymentRequest(
    var pattern_id: String,
    var amount: Double,
    var amount_due: Double,
    var comment: String,
    var message: String,
    var to: String
)