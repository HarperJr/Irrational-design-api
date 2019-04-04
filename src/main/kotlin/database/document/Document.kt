package database.document

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

abstract class Document<T> {
    @BsonId val id: Id<T> = newId()
}