package database.transaction

import kotlinx.coroutines.CoroutineScope

interface Transaction {
    suspend fun runInTx(action: suspend CoroutineScope.() -> Unit)

    suspend fun <T> callInTx(call: suspend CoroutineScope.() -> T): T
}