import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object FileManager {
    val ARTS_PATH: Path = Paths.get("/arts")

    suspend fun save(path: Path, source: ByteArray): String = coroutineScope {
        withContext(Dispatchers.IO) {
            val file = File(path.toUri())
                .apply {
                    if (!exists()) {
                        parentFile.mkdirs()
                        createNewFile()
                    }
                    outputStream().use { writeBytes(source) }
                }
            file.absolutePath
        }
    }

    suspend fun read(path: Path): ByteArray = coroutineScope {
        withContext(Dispatchers.IO) {
            Files.readAllBytes(path)
        }
    }
}