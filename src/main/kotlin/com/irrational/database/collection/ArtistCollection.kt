package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Artist
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class ArtistCollection(private val collection: CoroutineCollection<Artist>) :
    DocumentCollection<Artist>(collection) {

    suspend fun findByName(name: String) = collection.findOne(Artist::name eq name)
}
