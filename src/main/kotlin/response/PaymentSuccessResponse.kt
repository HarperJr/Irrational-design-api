package response

data class PaymentSuccessResponse(
    override val status: String = "success",
    var payment_id: String,
    var balance: Double
): PaymentResponse()