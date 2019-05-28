package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Avatar
import org.litote.kmongo.coroutine.CoroutineCollection

class AvatarCollection(private val collection: CoroutineCollection<Avatar>) :
    DocumentCollection<Avatar>(collection) {
}
