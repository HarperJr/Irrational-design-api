package interactor.payment

import di.AppComponent
import response.PaymentResponse


interface PaymentLoader {

    suspend fun requestPayment(
        to: String,
        amount: Double,
        message: String,
        initiator: String
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