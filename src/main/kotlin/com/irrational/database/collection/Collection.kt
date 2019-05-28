package com.irrational.database.collection

import com.irrational.database.document.Document
import org.litote.kmongo.Id

interface Collection<T : Document<T>> {

    suspend fun all(): List<T>

    suspend fun find(id: Id<T>): T?

    suspend fun find(ids: List<Id<T>>): List<T>

    suspend fun delete(id: Id<T>)

    suspend fun delete(ids: List<Id<T>>)

    suspend fun update(t: T)

    suspend fun insert(t: T)

    suspend fun insert(t: List<T>)

    suspend fun contains(t: T): Boolean
}