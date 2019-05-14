package database.document

import org.litote.kmongo.Id

data class Artist(
    var name: String,
    var password: String,
    var email: String,
    var registered: Long,
    var avatarId: Id<Avatar>? = null,
    var cash: Double = 0.0,
    var paymentSources: List<PaymentSource>
) : Document<Artist>()