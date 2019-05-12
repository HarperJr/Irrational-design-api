package interactor.payment

import di.AppComponent
import payment.PaymentSource
import response.PaymentResponse


interface PaymentLoader {

    suspend fun requestPayment(
        patternId: String,
        to: String,
        amount: Double,
        amountDue: Double,
        comment: String,
        message: String
    ): PaymentResponse

    suspend fun processPayment(
        requestId: String,
        moneySource: PaymentSource,
        csc: String
    ): PaymentResponse

    companion object : PaymentLoader by INSTANCE
}

private val INSTANCE = AppComponent.paymentLoader()