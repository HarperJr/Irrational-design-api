package request

import payment.PaymentSource

data class PaymentProcessRequest(
    var request_id: String,
    var csc: String,
    var moneySource: PaymentSource
)