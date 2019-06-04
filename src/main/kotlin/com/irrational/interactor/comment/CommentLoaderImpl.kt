package com.irrational.interactor.comment

import com.irrational.database.collection.ArtistCollection
import com.irrational.database.collection.AvatarCollection
import com.irrational.database.collection.CommentCollection
import com.irrational.database.collection.PostCollection
import com.irrational.database.document.Comment
import com.irrational.response.AuthorResponse
import com.irrational.response.AvatarResponse
import com.irrational.response.CommentResponse
import com.irrational.utils.ApiException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.litote.kmongo.id.toId
import org.litote.kmongo.toId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentLoaderImpl @Inject constructor(
    private val commentCollection: CommentCollection,
    private val postCollection: PostCollection,
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection
) : CommentLoader {

    override suspend fun comments(postId: String): List<CommentResponse> = coroutineScope {
        withContext(Dispatchers.IO) {
            return@withContext commentCollection.allByPost(postId.toId())
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

    override suspend fun insert(postId: String, artistId: String, content: String): CommentResponse = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(postId.toId())
            post?.let {
                val artist = artistCollection.find(artistId.toId())!!
                val avatar = artist.avatarId?.let { avatarCollection.find(it) }
                val comment = Comment(
                    ObjectId("${artist.id}").toId(),
                    ObjectId("${post.id}").toId(),
                    content
                )
                commentCollection.insert(comment)
                CommentResponse(
                    author = AuthorResponse(
                        artist.id,
                        name = artist.name,
                        avatar = avatar?.let { AvatarResponse(it.link) }
                    ),
                    content = comment.content,
                    date = comment.date
                )
            } ?: throw ApiException(HttpStatusCode.BadRequest, "Unable to find post with id $postId")
        }
    }
}