package database.document

import org.litote.kmongo.Id

data class Payment(
    var requestId: String,
    var sender: Id<Artist>,
    var receiver: Id<Artist>,
    var amount: Double,
    var date: Long,
    var status: Boolean
) : Document<Payment>()