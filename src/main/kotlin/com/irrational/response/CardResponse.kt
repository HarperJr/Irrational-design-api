package com.irrational.response

import com.irrational.database.document.WalletCard
import org.litote.kmongo.Id

data class CardResponse(
    var id: Id<WalletCard>,
    var availableCash: Double
)