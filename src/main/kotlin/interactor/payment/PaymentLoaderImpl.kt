package interactor.payment

import database.collection.ArtistCollection
import database.collection.PaymentCollection
import database.collection.VirtualWalletCollection
import database.collection.WalletCardCollection
import database.document.Artist
import database.document.Payment
import org.litote.kmongo.Id
import response.PaymentErrorResponse
import response.PaymentResponse
import response.PaymentSuccessResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentLoaderImpl @Inject constructor(
    private val artistCollection: ArtistCollection,
    private val paymentCollection: PaymentCollection,
    private val virtualWalletCollection: VirtualWalletCollection,
    private var walletCardCollection: WalletCardCollection
) : PaymentLoader {

    override suspend fun requestPayment(
        senderId: Id<Artist>,
        receiverId: Id<Artist>,
        amount: Double,
        message: String
    ): PaymentResponse {
        val senderWallet = virtualWalletCollection.findByArtist(senderId)!! //Опасно так делать!
        val receiverWallet = virtualWalletCollection.findByArtist(receiverId)!! //Опасно так делать!

        val availableCard = walletCardCollection
            .findByWallet(senderWallet.id)
            .firstOrNull { it.cash >= amount }

        return when {
            availableCard != null -> {
                //Здесь ты ставишь флажок, что платишь картой, нет?
                throw NotImplementedError()
            }
            senderWallet.cash >= amount -> {
                //Здесь платим из кошелька напрямую
                throw NotImplementedError()
            }
            else -> PaymentErrorResponse(
                error = "payment_refused",
                errorDescription = "not enough cash"
            )
        }
    }

    override suspend fun processPaymentCard(
        paymentId: Id<Payment>,
        csc: Long
    ): PaymentResponse {
        val payment = paymentCollection.find(paymentId)
        val sender = artistCollection.find(payment!!.sender)!!
        val receiver = artistCollection.find(payment.receiver)!!

//        sender.paymentSources.forEach {
//            if (it.card.csc == csc) {
//                it.card.cash -= payment.amount
//                receiver.cash += payment.amount
//                payment.status = true
//
//                return PaymentSuccessResponse(
//                    requestId = requestId,
//                    message = "payment success"
//                )
//            }
//        }
        return PaymentErrorResponse(
            error = "payment_refused",
            errorDescription = "there is some problem with payment"
        )
    }

    override suspend fun processPaymentWallet(paymentId: Id<Payment>): PaymentResponse {
        val payment = paymentCollection.find(paymentId)
        val senderWallet = virtualWalletCollection.findByArtist(payment!!.sender)!!
        val receiverWallet = virtualWalletCollection.findByArtist(payment.receiver)!!

        return if (senderWallet.cash >= payment.amount) {
//            sender.cash -= payment.amount
//            receiver.cash += payment.amount
            PaymentSuccessResponse(
                paymentId = paymentId,
                message = "payment success"
            )
        } else {
            PaymentErrorResponse(
                error = "payment_refused",
                errorDescription = "not enough wallet cash"
            )
        }
    }
}
