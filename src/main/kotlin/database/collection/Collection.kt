package database.collection

import database.document.Document

interface Collection<T : Document> {

    suspend fun all(): List<T>

    suspend fun find(id: String): T?

    suspend fun find(idRange: List<String>): List<T>

    suspend fun delete(id: String)

    suspend fun delete(ids: List<String>)

    suspend fun update(t: T)

    suspend fun insert(t: T)

    suspend fun insert(t: List<T>)

    suspend fun contains(t: T): Boolean
}