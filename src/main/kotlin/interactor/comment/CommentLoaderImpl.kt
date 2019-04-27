package interactor.comment

import database.collection.ArtistCollection
import database.collection.AvatarCollection
import database.collection.CommentCollection
import database.document.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import response.AuthorResponse
import response.AvatarResponse
import response.CommentResponse
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentLoaderImpl @Inject constructor(
    private val commentCollection: CommentCollection,
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection
) : CommentLoader {
    override suspend fun comment(postId: String, author: String, content: String) {
        commentCollection.insert(
            Comment(
                artistId = author.toId(),
                postId = postId.toId(),
                content = content,
                date = Date().time
            )
        )
    }

    override suspend fun comments(id: String): List<CommentResponse> = coroutineScope {
        withContext(Dispatchers.IO) {
            return@withContext commentCollection.getAllByPost(id)
                .map {
                    val author = artistCollection.find(it.artistId)!!
                    val avatar = avatarCollection.find(author.avatarId)!!
                    CommentResponse(
                        author = AuthorResponse(
                            name = author.name,
                            permalink = author.permalink,
                            avatar = AvatarResponse(
                                link = avatar.link
                            )
                        ),
                        content = it.content,
                        date = it.date
                    )
                }
        }
    }
}