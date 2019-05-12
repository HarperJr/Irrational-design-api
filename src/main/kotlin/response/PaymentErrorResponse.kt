package response

data class PaymentErrorResponse(
    override val status: String = "refused",
    var error: String,
    var error_description: String
) : PaymentResponse()