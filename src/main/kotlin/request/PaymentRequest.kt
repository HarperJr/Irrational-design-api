package request

data class PaymentRequest(
    var patternId: String,
    var amount: Double,
    var amountDue: Double,
    var comment: String,
    var message: String,
    var to: String
)