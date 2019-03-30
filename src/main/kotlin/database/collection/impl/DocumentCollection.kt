package database.collection.impl

import com.mongodb.client.MongoCollection
import database.collection.Collection
import database.entity.Document
import org.bson.types.ObjectId
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.eq
import org.litote.kmongo.findOneById
import org.litote.kmongo.updateOne

open class DocumentCollection<T : Document>(private val collection: MongoCollection<T>) : Collection<T> {

    override fun all(): List<T> = collection.find().toList()

    override fun find(id: String): T? = collection.findOneById(ObjectId(id))

    override fun delete(id: String) {
        collection.deleteOneById(ObjectId(id))
    }

    override fun delete(ids: List<String>) {
        collection.deleteMany(Document::id eq ids)
    }

    override fun update(t: T) {
        collection.updateOne(t::id eq t.id, t)
    }

    override fun insert(t: T) {
        collection.insertOne(t)
    }

    override fun insert(t: List<T>) {
        collection.insertMany(t)
    }

    override fun contains(t: T): Boolean = collection.findOneById(ObjectId(t.id)) != null
}