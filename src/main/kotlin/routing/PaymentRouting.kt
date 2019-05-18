package routing

import authPayload
import interactor.payment.PaymentLoader
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import org.litote.kmongo.toId
import request.PaymentProcessRequest
import request.PaymentRequest

fun Routing.paymentRouting() {
    authenticate {
        post("/payment/request-payment") {
            val paymentData = call.receive<PaymentRequest>()
            val response = PaymentLoader.requestPayment(
                amount = paymentData.amount,
                message = paymentData.message,
                receiverId = paymentData.to.toId(),
                senderId = call.authPayload().artistId.toId()
            )
            call.respond(response)
        }

        post("/payment/process-payment-card") {
            val paymentData = call.receive<PaymentProcessRequest>()
            val response = PaymentLoader.processPaymentCard(
                paymentId = paymentData.paymentId,
                csc = paymentData.csc.toLong()
            )
            call.respond(response)
        }

        post("/payment/process-payment-wallet") {
            val paymentData = call.receive<PaymentProcessRequest>()
            val response = PaymentLoader.processPaymentWallet(
                paymentId = paymentData.paymentId
            )
            call.respond(response)
        }
    }
}