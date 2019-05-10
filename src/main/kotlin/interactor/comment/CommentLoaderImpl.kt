package interactor.comment

import database.collection.ArtistCollection
import database.collection.AvatarCollection
import database.collection.CommentCollection
import database.collection.PostCollection
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
    private val postCollection: PostCollection,
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection
) : CommentLoader {

    override suspend fun comment(postId: String, author: String, content: String) = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(postId.toId())
            if (post == null) {
                throw Exception("Unable to find post with id $postId")
            } else {
                commentCollection.insert(
                    Comment(
                        artistId = author.toId(),
                        postId = post.id,
                        content = content,
                        date = Date().time
                    )
                )
            }
        }
    }

    override suspend fun comments(id: String): List<CommentResponse> = coroutineScope {
        withContext(Dispatchers.IO) {
            return@withContext commentCollection.getAllByPost(id)
                .map { comment ->
                    val author = artistCollection.find(comment.artistId)!!
                    val avatar = author.avatarId?.let { avatarCollection.find(it) }
                    CommentResponse(
                        author = AuthorResponse(
                            name = author.name,
                            avatar = avatar?.let { AvatarResponse(it.link) }
                        ),
                        content = comment.content,
                        date = comment.date
                    )
                }
        }
    }
}