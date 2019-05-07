package di

import api.YandexMoneyApi
import dagger.Subcomponent
import di.module.ApiModule

@Subcomponent(
    modules = [
        ApiModule::class
    ]
)
interface ApiComponent {
    fun yandexApi(): YandexMoneyApi
}