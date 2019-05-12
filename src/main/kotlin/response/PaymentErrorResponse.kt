package response

data class PaymentErrorResponse(
    override val status: String = "refused",
    var error: String,
    var errorDescription: String
) : PaymentResponse()