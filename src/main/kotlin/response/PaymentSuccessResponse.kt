package response

data class PaymentSuccessResponse(
    override val status: String = "success",
    var paymentId: String,
    var balance: Double
): PaymentResponse()