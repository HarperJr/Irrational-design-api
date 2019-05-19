package interactor.payment

import database.document.Artist
import database.document.Payment
import di.AppComponent
import org.litote.kmongo.Id
import request.CardRequest
import response.StatusResponse


interface PaymentLoader {

    suspend fun requestPayment(
        senderId: Id<Artist>,
        receiverId: Id<Artist>,
        amount: Double
    ): StatusResponse

    suspend fun processPaymentCard(
        paymentId: Id<Payment>,
        csc: Long
    ): StatusResponse

    suspend fun processPaymentWallet(
        paymentId: Id<Payment>
    ): StatusResponse

    suspend fun rejectPayment(
        paymentId: Id<Payment>
    ): StatusResponse

    suspend fun addCard(
        cardData: CardRequest
    ): StatusResponse

    suspend fun addWallet(
        owner: Id<Artist>
    ): StatusResponse

    companion object : PaymentLoader by INSTANCE
}

private val INSTANCE = AppComponent.paymentLoader()