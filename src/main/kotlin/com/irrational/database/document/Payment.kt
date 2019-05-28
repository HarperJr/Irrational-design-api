package com.irrational.database.document

import org.litote.kmongo.Id
import java.util.*

data class Payment(
    var sender: Id<Artist>,
    var receiver: Id<Artist>,
    var amount: Double,
    var date: Long = Date().time,
    var status: PaymentStatus
) : Document<Payment>()

enum class PaymentStatus {
    PENDING, COMPLETED, REJECTED
}