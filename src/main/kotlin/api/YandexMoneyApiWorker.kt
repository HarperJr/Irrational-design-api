package api

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpMethod
import java.net.URI

class YandexMoneyApiWorker constructor(
    private val httpClient: HttpClient
) : YandexMoneyApi {

    override suspend fun authorize(redirectUri: String): HttpResponse {
        return httpClient.request(fromURI("/oauth/authorize")) {
            method = HttpMethod.Post
        }
    }

    override suspend fun token(): HttpResponse {
        return httpClient.post(fromURI("/oauth/token")) {

        }
    }

    override suspend fun requestPayment(
        to: String, amount: Long,
        message: String, comment: String
    ): HttpResponse {
        return httpClient.post()
    }

    private fun fromURI(to: String) = YANDEX_MONEY_URI.resolve(to).toString()

    companion object {
        private val YANDEX_MONEY_URI = URI.create("https://money.yandex.ru")
    }
}