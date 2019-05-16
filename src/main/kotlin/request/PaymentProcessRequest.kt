package request

import database.document.PaymentSource
import com.google.gson.annotations.SerializedName

data class PaymentProcessRequest(
    @SerializedName("request_id") var requestId: String,
    var csc: String,
    @SerializedName("money_source") var moneySource: PaymentSource
)