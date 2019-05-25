package database.collection.impl

import database.collection.Collection
import database.document.Document
import org.bson.BsonObjectId
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.`in`
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

open class DocumentCollection<T : Document<T>>(private val collection: CoroutineCollection<T>) : Collection<T> {

    override suspend fun all(): List<T> = collection.find().toList()

    override suspend fun find(id: Id<T>): T? = collection.findOneById(ObjectId(id.toString()))

    override suspend fun find(ids: List<Id<T>>): List<T> {
        return collection.find(Document<T>::id `in` ids).toList()
    }

    override suspend fun delete(id: Id<T>) {
        collection.deleteOne(Document<T>::id eq id)
    }

    override suspend fun delete(ids: List<Id<T>>) {
        collection.deleteMany(Document<T>::id `in` ids)
    }

    override suspend fun update(t: T) {
        collection.updateOne(Document<T>::id eq t.id, t)
    }

    override suspend fun insert(t: T) {
        collection.insertOne(t)
    }

    override suspend fun insert(t: List<T>) {
        collection.insertMany(t)
    }

    override suspend fun contains(t: T): Boolean = collection.findOne(Document<T>::id eq t.id) != null
}