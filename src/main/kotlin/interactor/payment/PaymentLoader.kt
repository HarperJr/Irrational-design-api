package interactor.payment

import database.document.Artist
import di.AppComponent
import org.litote.kmongo.Id
import response.PaymentResponse


interface PaymentLoader {

    suspend fun requestPayment(
        senderId: Id<Artist>,
        receiverId: Id<Artist>,
        amount: Double,
        message: String
    ): PaymentResponse

    suspend fun processPaymentCard(
        requestId: String,
        csc: Long
    ): PaymentResponse

    suspend fun processPaymentWallet(
        requestId: String
    ): PaymentResponse

    companion object : PaymentLoader by INSTANCE
}

private val INSTANCE = AppComponent.paymentLoader()