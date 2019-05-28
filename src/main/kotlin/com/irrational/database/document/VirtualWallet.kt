package com.irrational.database.document

import org.litote.kmongo.Id

data class VirtualWallet(
    var artistId: Id<Artist>,
    var cash: Double
) : Document<VirtualWallet>()