package interactor.payment

import database.collection.ArtistCollection
import database.collection.PaymentCollection
import database.collection.VirtualWalletCollection
import database.collection.WalletCardCollection
import database.document.Artist
import database.document.Payment
import database.document.PaymentStatus
import io.ktor.http.HttpStatusCode
import org.litote.kmongo.Id
import response.*
import utils.ApiException
import java.util.*
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
        amount: Double
    ): PaymentResponse {
        val senderWallet = virtualWalletCollection.findByArtist(senderId) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Sender wallet not found"
        )

        val receiverWallet = virtualWalletCollection.findByArtist(receiverId) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Receiver wallet not found"
        )

        val availableCards = walletCardCollection
            .findByWallet(senderWallet.id)
            .filter { it.cash >= amount }

        if (senderWallet.cash >= amount || availableCards.isNotEmpty()) {
            val payment = Payment(
                sender = senderId,
                receiver = receiverId,
                status = PaymentStatus.PENDING,
                date = Date().time,
                amount = amount
            )
            paymentCollection.insert(payment)
            return PaymentAcceptResponse(
                paymentId = payment.id,
                availableCards = availableCards.map {
                    CardResponse(
                        id = it.id,
                        availableCash = it.cash
                    )
                },
                availableCash = senderWallet.cash,
                walletId = receiverWallet.id
            )
        }

        return PaymentErrorResponse(
            error = "payment_refused",
            errorDescription = "not enough cash"
        )
    }

    override suspend fun processPaymentCard(
        paymentId: Id<Payment>,
        csc: Long
    ): PaymentResponse {
        val payment = paymentCollection.find(paymentId) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Payment not found"
        )

        if (payment.status != PaymentStatus.PENDING) return PaymentErrorResponse(
            error = "payment_decline",
            errorDescription = "payment already complited or rejected"
        )

        val senderWallet = virtualWalletCollection.findByArtist(payment.sender) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Sender wallet not found"
        )

        val senderCard =
            walletCardCollection.findByWallet(senderWallet.id).firstOrNull { it.csc == csc } ?: throw ApiException(
                statusCode = HttpStatusCode.BadRequest,
                errorMessage = "Card not found"
            )

        val receiver = artistCollection.find(payment.receiver) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Receiver not found"
        )

        val receiverWallet = virtualWalletCollection.findByArtist(receiver.id) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Receiver wallet not found"
        )

        senderCard.cash -= payment.amount
        walletCardCollection.update(senderCard)

        receiverWallet.cash += payment.amount
        virtualWalletCollection.update(receiverWallet)

        payment.status = PaymentStatus.COMPLETED
        paymentCollection.update(payment)

        return PaymentSuccessResponse(
            paymentId = payment.id,
            message = "Payment successfully applied"
        )
    }

    override suspend fun processPaymentWallet(paymentId: Id<Payment>): PaymentResponse {
        val payment = paymentCollection.find(paymentId) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Payment not found"
        )

        if (payment.status != PaymentStatus.PENDING) return PaymentErrorResponse(
            error = "payment_decline",
            errorDescription = "payment already complited or rejected"
        )

        val sender = artistCollection.find(payment.sender) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Receiver not found"
        )

        val senderWallet = virtualWalletCollection.findByArtist(sender.id) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Receiver wallet not found"
        )

        val receiver = artistCollection.find(payment.receiver) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Receiver not found"
        )

        val receiverWallet = virtualWalletCollection.findByArtist(receiver.id) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Receiver wallet not found"
        )

        senderWallet.cash -= payment.amount
        virtualWalletCollection.update(senderWallet)

        receiverWallet.cash += payment.amount
        virtualWalletCollection.update(receiverWallet)

        payment.status = PaymentStatus.COMPLETED
        paymentCollection.update(payment)

        return PaymentSuccessResponse(
            paymentId = payment.id,
            message = "Payment successfully applied"
        )
    }

    override suspend fun rejectPayment(paymentId: Id<Payment>): PaymentResponse {
        val payment = paymentCollection.find(paymentId) ?: throw ApiException(
            statusCode = HttpStatusCode.NotFound,
            errorMessage = "Payment not found"
        )

        payment.status = PaymentStatus.REJECTED
        paymentCollection.update(payment)

        return PaymentSuccessResponse(
            paymentId = payment.id,
            message = "Payment successfully rejected"
        )
    }
}
