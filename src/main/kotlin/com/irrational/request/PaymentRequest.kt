package com.irrational.request

import com.irrational.database.document.Artist
import org.litote.kmongo.Id

data class PaymentRequest(
    var amount: Double,
    var message: String,
    var to: Id<Artist>
)