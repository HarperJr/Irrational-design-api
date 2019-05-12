package payment

class Cards_PaymentSource(
    override var allowed: Boolean,
    var csc_required: Boolean,
    var items: List<Card>
) : PaymentSource()