package routing

import gson
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
        val body = call.receive<String>()
        val paymentData = gson.fromJson(body, PaymentRequest::class.java)
        var response = PaymentLoader.requestPayment(
            pattern_id = paymentData.pattern_id,
            amount = paymentData.amount,
            amount_due = paymentData.amount_due,
            comment = paymentData.comment,
            message = paymentData.message,
            to = paymentData.to
        )
        call.respond(response)
    }

    post("/payment/process-payment") {
        val body = call.receive<String>()
        val paymentData = gson.fromJson(body, PaymentProcessRequest::class.java)
        var response = PaymentLoader.processPayment(
            request_id = paymentData.request_id,
            csc = paymentData.csc,
            moneySource = paymentData.moneySource
        )
        call.respond(response)
    }
}