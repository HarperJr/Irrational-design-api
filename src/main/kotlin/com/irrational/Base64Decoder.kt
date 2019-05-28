package com.irrational

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Specially developed decoder for irrational design client images base64 encoded
 */
object Base64Decoder {
    private val base64Decoder = Base64.getDecoder()

    suspend fun decodeImage(bytes: ByteArray): ImageFile = coroutineScope {
        withContext(Dispatchers.IO) {
            String(bytes).let {
                val divider = it.indexOf(',')
                val metadata = it.substring(0, divider).split(";")
                val data = it.substring(divider + 1)

                val imageData = metadata.firstOrNull() ?: throw Exception("Unhandled metadata")
                val formatType = if (imageData.startsWith(DATA_IMAGE_PREFIX)) {
                    imageData.substring(DATA_IMAGE_PREFIX.length)
                } else throw Exception("Image doesn't contain image data")

                val uniqueName = UUID.randomUUID().toString()
                val imageName = "$uniqueName.$formatType"

                ImageFile(imageName, base64Decoder.decode(data))
            }
        }
    }
}

private const val DATA_IMAGE_PREFIX = "data:image/"