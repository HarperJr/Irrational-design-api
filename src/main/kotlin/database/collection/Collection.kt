package database.collection

import database.entity.Document

interface Collection<T : Document> {

    fun all(): List<T>

    fun find(id: String): T?

    fun delete(id: String)

    fun delete(ids: List<String>)

    fun update(t: T)

    fun insert(t: T)

    fun insert(t: List<T>)

    fun contains(t: T): Boolean
}