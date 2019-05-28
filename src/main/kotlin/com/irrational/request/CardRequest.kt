package com.irrational.request

import com.irrational.database.document.Artist
import org.litote.kmongo.Id

data class CardRequest(
    var owner: Id<Artist>,
    var panFragment: String,
    var type: Int,
    var csc: Long
)