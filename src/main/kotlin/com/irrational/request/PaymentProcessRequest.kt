package com.irrational.request

import com.google.gson.annotations.SerializedName
import com.irrational.database.document.Payment
import com.irrational.database.document.WalletCard
import org.litote.kmongo.Id

data class PaymentProcessRequest(
    @SerializedName("payment_id") var paymentId: Id<Payment>,
    var csc: String,
    @SerializedName("money_source") var moneySource: WalletCard
)