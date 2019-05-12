package routing

import interactor.payment.PaymentLoader
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import request.PaymentProcessRequest
import request.PaymentRequest

fun Routing.paymentRouting() {
    post("/payment/request-payment") {
        val paymentData = call.receive<PaymentRequest>()
        val response = PaymentLoader.requestPayment(
            patternId = paymentData.patternId,
            amount = paymentData.amount,
            amountDue = paymentData.amountDue,
            comment = paymentData.comment,
            message = paymentData.message,
            to = paymentData.to
        )
        call.respond(response)
    }

    post("/payment/process-payment") {
        val paymentData = call.receive<PaymentProcessRequest>()
        val response = PaymentLoader.processPayment(
            requestId = paymentData.requestId,
            csc = paymentData.csc,
            moneySource = paymentData.moneySource
        )
        call.respond(response)
    }
}