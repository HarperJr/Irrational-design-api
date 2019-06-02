package com.irrational

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

object FileManager {
    private const val ROOT_FOLDER_PATH = "/var/irrational-design"
    private val ARTS_FOLDER_PATH: Path = Paths.get(ROOT_FOLDER_PATH, "/images")
    private val AVATARS_FOLDER_PATH: Path = Paths.get(ROOT_FOLDER_PATH, "/avatars")

    suspend fun save(folder: File, name: String, source: ByteArray): String = coroutineScope {
        withContext(Dispatchers.IO) {
            Paths.get(folder.absolutePath, name)
                .let { path ->
                    val file = File(path.toUri())
                    if (!file.exists()) {
                        file.parentFile.mkdirs()
                        file.createNewFile()
                    }
                    file.outputStream().use { it.write(source) }
                    file.absolutePath
                }
        }
    }

    suspend fun delete(folder: File, name: String) = coroutineScope {
        withContext(Dispatchers.IO) {
            Paths.get(folder.absolutePath, name)
                .let { path ->
                    val file = File(path.toUri())
                    if (file.exists()) {
                        file.delete()
                    }
                }
        }
    }

    fun artsFolder(): File {
        val folder = File(ARTS_FOLDER_PATH.toUri())
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return folder
    }

    fun avatarsFolder(): File {
        val folder = File(AVATARS_FOLDER_PATH.toUri())
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return folder
    }
}

data class ImageFile(
    var name: String,
    var bytes: ByteArray
)