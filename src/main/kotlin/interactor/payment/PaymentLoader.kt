package interactor.payment

import database.document.Artist
import database.document.Payment
import di.AppComponent
import org.litote.kmongo.Id
import response.PaymentResponse


interface PaymentLoader {

    suspend fun requestPayment(
        senderId: Id<Artist>,
        receiverId: Id<Artist>,
        amount: Double
    ): PaymentResponse

    suspend fun processPaymentCard(
        paymentId: Id<Payment>,
        csc: Long
    ): PaymentResponse

    suspend fun processPaymentWallet(
        paymentId: Id<Payment>
    ): PaymentResponse

    suspend fun rejectPayment(
        paymentId: Id<Payment>
    ): PaymentResponse

    companion object : PaymentLoader by INSTANCE
}

private val INSTANCE = AppComponent.paymentLoader()