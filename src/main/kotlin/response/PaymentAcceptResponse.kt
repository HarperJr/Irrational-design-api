package response

import payment.PaymentSource

data class PaymentAcceptResponse(
    override val status: String = "success",
    var money_source: List<PaymentSource>,
    var request_id: String,
    var contract_amount: Double,
    var balance: Double
) : PaymentResponse()