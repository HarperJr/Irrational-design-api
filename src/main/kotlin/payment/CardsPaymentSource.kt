package payment

class CardsPaymentSource(
    /**
     * ???
     */
    override var allowed: Boolean,
    /**
     * CVV, CVC код карты
     */
    var cscRequired: Boolean,
    /**
     * ???
     */
    var items: List<Card>
) : PaymentSource()