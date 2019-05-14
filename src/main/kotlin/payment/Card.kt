package payment

data class Card(
    var id: Long,
    /**
     * Часть номера карты
     */
    var panFragment: String,
    /**
     * Тип карты (Visa/MasterCard/...)
     */
    var type: String,
    /**
     * Текущий баланс
     */
    var csc: Long,
    var cash: Double
)