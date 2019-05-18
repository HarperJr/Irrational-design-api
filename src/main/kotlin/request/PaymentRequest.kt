package request

import database.document.Artist
import org.litote.kmongo.Id

data class PaymentRequest(
    var amount: Double,
    var message: String,
    var to: Id<Artist>
)