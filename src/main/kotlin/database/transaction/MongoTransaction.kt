package database.transaction

import database.Database
import kotlinx.coroutines.CoroutineScope

class MongoTransaction(
    private val database: Database
) : Transaction {

    override suspend fun runInTx(action: suspend CoroutineScope.() -> Unit) = database.runInTx(action)

    override suspend fun <T> callInTx(call: suspend CoroutineScope.() -> T): T = database.callInTx(call)
}