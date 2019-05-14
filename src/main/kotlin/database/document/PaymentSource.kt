package database.document

import org.litote.kmongo.Id
import payment.Card

data class PaymentSource(
    var artistId: Id<Artist>,
    var card: Card
) : Document<PaymentSource>()