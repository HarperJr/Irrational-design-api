package payment

data class WalletPaymentSource(
    override var allowed: Boolean
) : PaymentSource()