package request

import com.google.gson.annotations.SerializedName
import database.document.Payment
import database.document.WalletCard
import org.litote.kmongo.Id

data class PaymentProcessRequest(
    @SerializedName("payment_id") var paymentId: Id<Payment>,
    var csc: String,
    @SerializedName("money_source") var moneySource: WalletCard
)