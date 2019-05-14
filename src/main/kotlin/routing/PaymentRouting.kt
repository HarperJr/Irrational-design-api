package routing

import claim
import interactor.payment.PaymentLoader
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import jwtPayload
import request.PaymentProcessRequest
import request.PaymentRequest

fun Routing.paymentRouting() {
    post("/payment/request-payment") {
        val paymentData = call.receive<PaymentRequest>()
        val response = PaymentLoader.requestPayment(
            amount = paymentData.amount,
            message = paymentData.message,
            to = paymentData.to,
            initiator = call.jwtPayload()!!.claim("artistId")
        )
        call.respond(response)
    }

    post("/payment/process-payment-card") {
        val paymentData = call.receive<PaymentProcessRequest>()
        val response = PaymentLoader.processPaymentCard(
            requestId = paymentData.requestId,
            csc = paymentData.csc.toLong()
        )
        call.respond(response)
    }

    post("/payment/process-payment-wallet") {
        val paymentData = call.receive<PaymentProcessRequest>()
        val response = PaymentLoader.processPaymentWallet(
            requestId = paymentData.requestId
        )
        call.respond(response)
    }
}