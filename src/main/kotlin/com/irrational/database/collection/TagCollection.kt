package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Tag
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class TagCollection(private val collection: CoroutineCollection<Tag>) :
    DocumentCollection<Tag>(collection) {

    suspend fun findByName(name: String): Tag? {
        return collection.findOne(Tag::name eq name)
    }
}