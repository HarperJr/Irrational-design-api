package database.document

import org.litote.kmongo.Id

data class WalletCard(
    var walletId: Id<VirtualWallet>,
    /**
     * Последние 4 цифры карты
     */
    var panFragment: String,
    /**
     * Тип карты (Visa/MasterCard/...)
     */
    var type: CardType,
    /**
     * CVC, CVV код
     */
    var csc: Long,
    /**
     * Текущий баланс
     */
    var cash: Double
) : Document<WalletCard>()

enum class CardType {
    VISA, MASTER_CARD, MAESTRO, AMERICAN_EXPRESS, MIR
}