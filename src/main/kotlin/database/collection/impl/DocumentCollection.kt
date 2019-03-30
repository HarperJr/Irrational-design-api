package database.collection.impl

import database.collection.Collection
import database.document.Document
import org.bson.types.ObjectId
import org.litote.kmongo.`in`
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

open class DocumentCollection<T : Document>(private val collection: CoroutineCollection<T>) : Collection<T> {

    override suspend fun all(): List<T> = collection.find().toList()

    override suspend fun find(id: String): T? = collection.findOneById(ObjectId(id))

    override suspend fun find(idRange: List<String>): List<T> = collection.find(Document::id `in` idRange).toList()

    override suspend fun delete(id: String) {
        collection.deleteOneById(ObjectId(id))
    }

    override suspend fun delete(ids: List<String>) {
        collection.deleteMany(Document::id eq ids)
    }

    override suspend fun update(t: T) {
        collection.updateOne(Document::id eq t.id, t)
    }

    override suspend fun insert(t: T) {
        collection.insertOne(t)
    }

    override suspend fun insert(t: List<T>) {
        collection.insertMany(t)
    }

    override suspend fun contains(t: T): Boolean = collection.findOne(Document::id eq t.id) != null
}