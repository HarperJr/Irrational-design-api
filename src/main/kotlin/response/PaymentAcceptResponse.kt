package response

import payment.PaymentSource

data class PaymentAcceptResponse(
    override val status: String = "success",
    /**
     * ???
     */
    var paymentSources: List<PaymentSource>,
    /**
     * Id запроса
     */
    var requestId: String,
    /**
     * ???
     */
    var contractAmount: Double,
    /**
     * Баланс
     */
    var balance: Double
) : PaymentResponse()