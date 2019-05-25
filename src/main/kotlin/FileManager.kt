import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

object FileManager {
    private val ROOT_FOLDER_PATH: Path = Paths.get("/var/irrational-design")
    private val ARTS_FOLDER_PATH: Path = ROOT_FOLDER_PATH.resolve("/images")

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

    fun artsFolder(): File {
        val folder = File(ARTS_FOLDER_PATH.toUri())
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return folder
    }
}