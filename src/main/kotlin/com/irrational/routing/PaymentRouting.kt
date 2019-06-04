package com.irrational.routing

import com.irrational.authPayload
import com.irrational.interactor.payment.PaymentLoader
import com.irrational.request.CardRequest
import com.irrational.request.PaymentProcessRequest
import com.irrational.request.PaymentRequest
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import org.litote.kmongo.toId

fun Routing.payment() {
    authenticate {
        post("/payment/request-payment") {
            val paymentData = call.receive<PaymentRequest>()
            val response = PaymentLoader.requestPayment(
                amount = paymentData.amount,
                receiverId = paymentData.to,
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

        post("/payment/reject-payment") {
            val paymentData = call.receive<PaymentProcessRequest>()
            val response = PaymentLoader.rejectPayment(
                paymentId = paymentData.paymentId
            )
            call.respond(response)
        }

        post("/payment/add-card") {
            val cardData = call.receive<CardRequest>()
            val response = PaymentLoader.addCard(cardData = cardData)
            call.respond(response)
        }

        post("/payment/add-wallet") {
            val response = PaymentLoader.addWallet(owner = call.authPayload().artistId.toId())
            call.respond(response)
        }
    }
}