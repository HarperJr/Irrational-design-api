package payment

data class Wallet_PaymentSource(
    override var allowed: Boolean
) : PaymentSource()