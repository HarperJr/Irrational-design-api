package com.irrational

import com.sun.org.apache.xml.internal.security.utils.Base64
import java.io.InputStream
import java.util.*

/**
 * Specially developed decoder for irrational design client images base64 encoded
 */
object Base64Decoder {
    fun decodeImage(stream: InputStream): ImageFile {
        return stream.use { s ->
            val header = CharArray(23).let {
                s.reader().read(it)
                String(it)
            }.split(";")
            val data = header[0]
            val formatType = if (data.startsWith(DATA_IMAGE_PREFIX)) {
                data.substring(DATA_IMAGE_PREFIX.length)
            } else throw Exception("Invalid image format type")

            val uniqueName = UUID.randomUUID().toString()
            val name = "$uniqueName.$formatType"
            val decodedBytes = Base64.decode(s.readBytes())

            ImageFile(name = name, bytes = decodedBytes)
        }
    }
}

private const val DATA_IMAGE_PREFIX = "data:image/"