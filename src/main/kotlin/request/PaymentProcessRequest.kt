package request

import database.document.PaymentSource

data class PaymentProcessRequest(
    var requestId: String,
    var csc: String,
    var moneySource: PaymentSource
)