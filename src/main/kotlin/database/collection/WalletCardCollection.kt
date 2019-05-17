package database.collection

import database.collection.impl.DocumentCollection
import database.document.VirtualWallet
import database.document.WalletCard
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class WalletCardCollection(private val collection: CoroutineCollection<WalletCard>) :
    DocumentCollection<WalletCard>(collection) {

    suspend fun findByWallet(walletId: Id<VirtualWallet>): List<WalletCard> {
        return collection.find(WalletCard::walletId eq walletId).toList()
    }
}