package response

import database.document.PaymentSource


data class PaymentAcceptResponse(
    override val status: String = "success",

    var wallet: Boolean,

    var paymentSources: List<PaymentSource>,
    /**
     * Id запроса
     */
    var requestId: String
) : PaymentResponse()