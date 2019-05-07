package di.module

import api.YandexMoneyApi
import api.YandexMoneyApiWorker
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.util.KtorExperimentalAPI

@Module
class ApiModule {

    @KtorExperimentalAPI
    @Provides
    fun provideHttpClient() = HttpClient(CIO) {
        install(JsonFeature) { serializer = GsonSerializer() }
    }

    @Provides
    fun provideYandexMoneyApi(httpClient: HttpClient): YandexMoneyApi = YandexMoneyApiWorker(httpClient)
}