package com.irrational.interactor.payment

import com.irrational.database.collection.ArtistCollection
import com.irrational.database.collection.PaymentCollection
import com.irrational.database.collection.VirtualWalletCollection
import com.irrational.database.collection.WalletCardCollection
import com.irrational.database.document.*
import com.irrational.request.CardRequest
import com.irrational.response.*
import com.irrational.utils.ApiException
import io.ktor.http.HttpStatusCode
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.toId
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

    override suspend fun addCard(cardData: CardRequest, owner: Id<Artist>): StatusResponse {
        var wallet = virtualWalletCollection.findByArtist(owner)

        if (wallet == null) {
            addWallet(owner)
            wallet = virtualWalletCollection.findByArtist(owner) ?: throw ApiException(
                statusCode = HttpStatusCode.InternalServerError,
                errorMessage = "something wrong while add wallet"
            )
        }


        walletCardCollection.insert(
            WalletCard(
                walletId = wallet.id,
                csc = cardData.csc,
                cash = 0.0,
                panFragment = cardData.panFragment,
                type = CardType.by(cardData.type)
            )
        )

        return SuccessResponse(
            message = "Card successfully added"
        )
    }

    override suspend fun addWallet(owner: Id<Artist>): StatusResponse {

        if (virtualWalletCollection.findByArtist(owner) != null) return PaymentErrorResponse(
            error = "already_exist",
            errorDescription = "owner already have registered wallet"
        )

        virtualWalletCollection.insert(
            VirtualWallet(
                artistId = ObjectId("$owner").toId(),
                cash = 0.0
            )
        )

        return SuccessResponse(
            message = "Wallet successfully added"
        )
    }

    override suspend fun requestPayment(
        senderId: Id<Artist>,
        receiverId: Id<Artist>,
        amount: Double
    ): StatusResponse {
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
    ): StatusResponse {
        val payment = paymentCollection.find(paymentId) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "Payment not found"
        )

        if (payment.status != PaymentStatus.PENDING) return PaymentErrorResponse(
            error = "payment_decline",
            errorDescription = "payment already completed or rejected"
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
        if (senderCard.cash < payment.amount) return PaymentErrorResponse(
            error = "payment_refused",
            errorDescription = "not enough cash"
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

    override suspend fun processPaymentWallet(paymentId: Id<Payment>): StatusResponse {
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

        if (senderWallet.cash < payment.amount) return PaymentErrorResponse(
            error = "payment_refused",
            errorDescription = "not enough cash"
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

    override suspend fun rejectPayment(paymentId: Id<Payment>): StatusResponse {
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
