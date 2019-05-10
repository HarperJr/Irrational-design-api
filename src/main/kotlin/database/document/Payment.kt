package database.document

import org.litote.kmongo.Id

data class Payment(
    var sender: Id<Artist>,
    var receiver: Id<Artist>,
    var amount: Long,
    var date: Long
) : Document<Payment>()