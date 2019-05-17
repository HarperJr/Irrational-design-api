package database.collection

import database.collection.impl.DocumentCollection
import database.document.Artist
import database.document.VirtualWallet
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class VirtualWalletCollection(private val collection: CoroutineCollection<VirtualWallet>) :
    DocumentCollection<VirtualWallet>(collection) {

    suspend fun findByArtist(artistId: Id<Artist>): VirtualWallet? {
        return collection.findOne(VirtualWallet::artistId eq artistId)
    }
}