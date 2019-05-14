package response

data class PaymentSuccessResponse(
    override val status: String = "success",
    var requestId: String,
    var message: String
) : PaymentResponse()