package interactor.payment

import database.collection.ArtistCollection
import database.collection.PaymentCollection
import database.document.Payment
import database.document.PaymentSource
import org.litote.kmongo.toId
import response.PaymentAcceptResponse
import response.PaymentErrorResponse
import response.PaymentResponse
import response.PaymentSuccessResponse
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentLoaderImpl @Inject constructor(
    private val artistCollection: ArtistCollection,
    private val paymentCollection: PaymentCollection
) : PaymentLoader {

    override suspend fun requestPayment(
        to: String,
        amount: Double,
        message: String,
        initiator: String
    ): PaymentResponse {

        val paymentInitiator = artistCollection.find(initiator.toId())!!
        val requestId = (paymentInitiator.id.toString() + to + amount.toString()).hashCode().toString()

        paymentCollection.insert(
            Payment(
                requestId = requestId,
                sender = paymentInitiator.id,
                receiver = to.toId(),
                amount = amount,
                date = Date().time,
                status = false
            )
        )
        val paymentSources: MutableList<PaymentSource> = mutableListOf()
        paymentInitiator.paymentSources.forEach {
            if (it.card.cash >= amount) {
                paymentSources.add(it)
            }
        }
        return if ((paymentInitiator.cash >= amount) or (paymentSources.size > 0)) {
            PaymentAcceptResponse(
                wallet = paymentInitiator.cash >= amount,
                paymentSources = paymentInitiator.paymentSources,
                requestId = requestId
            )
        } else {
            PaymentErrorResponse(
                error = "payment_refused",
                errorDescription = "not enough cash"
            )
        }
    }

    override suspend fun processPaymentCard(
        requestId: String,
        csc: Long
    ): PaymentResponse {
        val payment = paymentCollection.findByRequestId(requestId)
        val sender = artistCollection.find(payment!!.sender)!!
        val receiver = artistCollection.find(payment.receiver)!!

        sender.paymentSources.forEach {
            if (it.card.csc == csc) {
                it.card.cash -= payment.amount
                receiver.cash += payment.amount
                payment.status = true

                return PaymentSuccessResponse(
                    requestId = requestId,
                    message = "payment success"
                )
            }
        }
        return PaymentErrorResponse(
            error = "payment_refused",
            errorDescription = "there is some problem with payment"
        )
    }

    override suspend fun processPaymentWallet(requestId: String): PaymentResponse {

        val payment = paymentCollection.findByRequestId(requestId)
        val sender = artistCollection.find(payment!!.sender)!!
        val receiver = artistCollection.find(payment.receiver)!!

        return if (sender.cash >= payment.amount) {
            sender.cash -= payment.amount
            receiver.cash += payment.amount
            PaymentSuccessResponse(
                requestId = requestId,
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