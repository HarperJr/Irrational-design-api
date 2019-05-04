import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.nio.file.Files
import java.nio.file.Path

object FileManager {

    suspend fun save(path: Path, source: ByteArray) = coroutineScope {
        withContext(Dispatchers.IO) {
            Files.createFile(path).toFile()
                .apply {
                    outputStream().use { writeBytes(source) }
                }
        }
    }

    suspend fun read(path: Path): ByteArray = coroutineScope {
        withContext(Dispatchers.IO) {
            Files.readAllBytes(path)
        }
    }
}