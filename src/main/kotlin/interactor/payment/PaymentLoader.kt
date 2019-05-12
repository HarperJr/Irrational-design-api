package interactor.payment

import di.AppComponent
import payment.PaymentSource
import response.PaymentResponse


interface PaymentLoader {

    suspend fun requestPayment(
        pattern_id: String,
        to: String,
        amount: Double,
        amount_due: Double,
        comment: String,
        message: String
    ): PaymentResponse

    suspend fun processPayment(
        request_id: String,
        moneySource: PaymentSource,
        csc: String
    ): PaymentResponse

    companion object : PaymentLoader by INSTANCE
}

private val INSTANCE = AppComponent.paymentLoader()