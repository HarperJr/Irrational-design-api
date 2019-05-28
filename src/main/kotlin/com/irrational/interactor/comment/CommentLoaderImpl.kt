package com.irrational.interactor.comment

import com.irrational.database.collection.ArtistCollection
import com.irrational.database.collection.AvatarCollection
import com.irrational.database.collection.CommentCollection
import com.irrational.database.collection.PostCollection
import com.irrational.database.document.Comment
import com.irrational.database.document.Post
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.Id
import org.litote.kmongo.toId
import com.irrational.response.AuthorResponse
import com.irrational.response.AvatarResponse
import com.irrational.response.CommentResponse
import com.irrational.utils.ApiException
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

    override suspend fun insert(postId: Id<Post>, author: String, content: String) = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(postId)
            if (post == null) {
                throw ApiException(
                    HttpStatusCode.BadRequest,
                    "Unable to find post with id $postId"
                )
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
                            author.id,
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