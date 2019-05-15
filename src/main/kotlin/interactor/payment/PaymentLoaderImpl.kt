package interactor.payment

import payment.PaymentSource
import response.PaymentErrorResponse
import response.PaymentResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentLoaderImpl @Inject constructor(

) : PaymentLoader {
    override suspend fun requestPayment(
        patternId: String,
        to: String,
        amount: Double,
        amountDue: Double,
        comment: String,
        message: String
    ): PaymentResponse {
        return PaymentErrorResponse(
            error = "payment_refused",
            errorDescription = "there is some problem with payment"
        )
    }

    override suspend fun processPayment(requestId: String, moneySource: PaymentSource, csc: String): PaymentResponse {
        return PaymentErrorResponse(
            error = "payment_refused",
            errorDescription = "there is some problem with payment"
        )
    }

}