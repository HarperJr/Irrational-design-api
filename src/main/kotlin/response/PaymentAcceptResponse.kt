package response

import database.document.Payment
import database.document.VirtualWallet
import org.litote.kmongo.Id


data class PaymentAcceptResponse(
    override val status: String = "success",
    var paymentId: Id<Payment>,
    var walletId: Id<VirtualWallet>,
    var availableCards: List<CardResponse>,
    var availableCash: Double
) : StatusResponse()