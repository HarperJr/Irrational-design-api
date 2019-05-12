package request

import payment.PaymentSource

data class PaymentProcessRequest(
    var requestId: String,
    var csc: String,
    var moneySource: PaymentSource
)