package interactor.payment

import payment.PaymentSource
import response.PaymentErrorResponse
import response.PaymentResponse
import javax.inject.Singleton

@Singleton
class PaymentLoaderImpl(

) : PaymentLoader {
    override suspend fun requestPayment(
        pattern_id: String,
        to: String,
        amount: Double,
        amount_due: Double,
        comment: String,
        message: String
    ): PaymentResponse {
        return PaymentErrorResponse(
            error = "payment_refused",
            error_description = "there is some problem with payment"
        )
    }

    override suspend fun processPayment(request_id: String, moneySource: PaymentSource, csc: String): PaymentResponse {
        return PaymentErrorResponse(
            error = "payment_refused",
            error_description = "there is some problem with payment"
        )
    }

}