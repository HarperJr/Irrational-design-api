package response

import database.document.Payment
import org.litote.kmongo.Id

data class PaymentSuccessResponse(
    override val status: String = "success",
    var paymentId: Id<Payment>,
    var message: String
) : StatusResponse()