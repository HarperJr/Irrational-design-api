package request

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("pattern_id") var patternId: String,
    var amount: Double,
    @SerializedName("amount_due") var amountDue: Double,
    var comment: String,
    var message: String,
    var to: String
)