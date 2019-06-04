package com.irrational.request

import com.irrational.database.document.Payment
import org.litote.kmongo.Id

data class PaymentProcessRequest(
    var paymentId: Id<Payment>,
    var csc: String
)