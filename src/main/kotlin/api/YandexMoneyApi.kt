package api

import di.AppComponent
import io.ktor.client.response.HttpResponse

interface YandexMoneyApi {
    suspend fun authorize(redirectUri: String): HttpResponse

    suspend fun token(): HttpResponse

    suspend fun requestPayment(to: String, amount: Long, message: String, comment: String): HttpResponse

    companion object : YandexMoneyApi by INSTANCE
}

private val INSTANCE = AppComponent
    .apiComponent()
    .yandexApi()