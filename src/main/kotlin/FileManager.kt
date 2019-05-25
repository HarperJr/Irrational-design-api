import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.awt.Image
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import javax.imageio.ImageIO

object FileManager {
    const val ARTS = "arts"
    private val ARTS_FOLDER_PATH: Path = Paths.get("/var/images")

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