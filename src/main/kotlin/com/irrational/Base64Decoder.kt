package com.irrational

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.util.*

/**
 * Specially developed decoder for irrational design client images base64 encoded
 */
object Base64Decoder {
    private val base64Decoder = Base64.getDecoder()

    suspend fun decodeImage(stream: InputStream): ImageFile = coroutineScope {
        withContext(Dispatchers.IO) {
            stream.use { s ->
                val header = ByteArray(23).let {
                    s.read(it)
                    String(it)
                }.split(";")
                val data = header[0]
                val formatType = if (data.startsWith(DATA_IMAGE_PREFIX)) {
                    data.substring(DATA_IMAGE_PREFIX.length)
                } else throw Exception("Invalid image format type")

                val uniqueName = UUID.randomUUID().toString()
                val name = "$uniqueName.$formatType"
                val decodedBytes = base64Decoder.decode(s.readBytes())

                ImageFile(name = name, bytes = decodedBytes)
            }
        }
    }
}

private const val DATA_IMAGE_PREFIX = "data:image/"