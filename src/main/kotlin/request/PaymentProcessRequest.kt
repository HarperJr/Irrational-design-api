package request

import com.google.gson.annotations.SerializedName
import database.document.WalletCard

data class PaymentProcessRequest(
    @SerializedName("request_id") var requestId: String,
    var csc: String,
    @SerializedName("money_source") var moneySource: WalletCard
)