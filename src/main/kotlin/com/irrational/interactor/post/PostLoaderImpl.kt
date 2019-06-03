package com.irrational.interactor.post

import com.irrational.FileManager
import com.irrational.ImageFile
import com.irrational.database.collection.*
import com.irrational.database.document.*
import com.irrational.database.transaction.Transaction
import com.irrational.response.*
import com.irrational.utils.ApiException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.Id
import org.litote.kmongo.toId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLoaderImpl @Inject constructor(
    private val transaction: Transaction, //todo need to resolve issue with transactions in mongo
    private val postCollection: PostCollection,
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection,
    private val artCollection: ArtCollection,
    private val tagCollection: TagCollection,
    private val categoryCollection: CategoryCollection,
    private val tagInPostCollection: TagInPostCollection,
    private val categoryInPostCollection: CategoryInPostCollection,
    private val commentCollection: CommentCollection,
    private val previewCollection: PreviewCollection,
    private val likeCollection: LikeCollection,
    private val bookmarkCollection: BookmarkCollection,
    private val followerCollection: FollowerCollection
) : PostLoader {

    override suspend fun delete(postId: Id<Post>, artistId: Id<Artist>): StatusResponse {
        val post = postCollection.find(postId) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "post not found"
        )
        if (post.artistId == artistId) {
            tagInPostCollection.deleteByPost(postId)
            categoryInPostCollection.deleteByPost(postId)
            artCollection.findByPost(postId)
                .forEach { FileManager.delete(FileManager.artsFolder(), it.link) }
            artCollection.deleteByPost(postId)
            postCollection.delete(postId)
            return SuccessResponse(
                message = "post deleted"
            )
        }

        throw ApiException(
            statusCode = HttpStatusCode.Forbidden,
            errorMessage = "Access denied"
        )
    }

    override suspend fun upload(
        post: Post, categories: List<String>,
        tags: List<String>, imageFiles: List<ImageFile>
    ) = coroutineScope {
        withContext(Dispatchers.IO) {
            val artsFolder = post.title
                .replace(' ', '_') //replace all whitespaces with _
                .trim()
                .toLowerCase()

            val categoriesInPost = categories.map {
                val category = categoryCollection.findByName(it)
                    ?: throw Exception("Unknown category")
                CategoryInPost(post.id, category.id)
            }
            val tagsInPost = tags.map {
                val tag = tagCollection.findByName(it)
                    ?: Tag(it).also { tag -> tagCollection.insert(tag) }
                TagInPost(post.id, tag.id)
            }
            val arts = imageFiles.map { image ->
                val name = "$artsFolder/${image.name}"
                FileManager.save(FileManager.artsFolder(), name, image.bytes)
                Art(post.id, name)
            }
            val preview = Preview(post.id, arts.first().link)

            postCollection.insert(post)
            categoryInPostCollection.insert(categoriesInPost)
            tagInPostCollection.insert(tagsInPost)
            previewCollection.insert(preview)
            artCollection.insert(arts)
        }
    }

    override suspend fun liked(artistId: String, id: String): LikedResponse = coroutineScope {
        withContext(Dispatchers.IO) {
            LikedResponse(likeCollection.liked(id.toId(), artistId.toId()))
        }
    }

    override suspend fun post(artistId: String?, id: String): PostResponse = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(id.toId()) ?: throw Exception("Unable to find post with id $id")
            val artist = artistCollection.find(post.artistId)!!
            val avatar = artist.avatarId?.let { avatarCollection.find(it) }
            val arts = artCollection.findByPost(post.id)
            val tags = let {
                val tagsInPost = tagInPostCollection.findByPost(post.id)
                tagCollection.find(tagsInPost).map { it.name }
            }
            val categories = let {
                val categoriesInPost = categoryInPostCollection.findByPost(post.id)
                categoryCollection.find(categoriesInPost).map { it.name }
            }
            return@withContext PostResponse(
                id = post.id,
                artist = ArtistResponse(
                    id = artist.id,
                    name = artist.name,
                    followed = artistId?.let { followerCollection.followed(artist.id, it.toId()) } ?: false,
                    follows = followerCollection.follows(artist.id).count(),
                    followers = followerCollection.followers(artist.id).count(),
                    email = artist.email,
                    avatar = avatar?.let { AvatarResponse(it.link) }
                ),
                arts = arts.map { ArtResponse(it.link) },
                title = post.title,
                subtitle = post.subtitle,
                description = post.description,
                likes = likeCollection.countByPost(id.toId()),
                bookmarks = bookmarkCollection.countByPost(id.toId()),
                tags = tags,
                categories = categories,
                date = post.date
            )
        }
    }

    override suspend fun posts(from: Int, to: Int, filter: String): List<FeedPostResponse> = coroutineScope {
        withContext(Dispatchers.IO) {
            when (filter) {
                "all" -> postCollection.allWithBoundary(from, to)
                "hot" -> postCollection.hotWithBoundary(from, to)
                "recommended" -> postCollection.recommendedWithBoundary(from, to)
                "most_viewed" -> postCollection.viewedWithBoundary(from, to)
                "most_rated" -> postCollection.ratedWithBoundary(from, to)
                "most_popular" -> postCollection.popularWithBoundary(from, to)
                else -> postCollection.byArtistWithBoundary(from, to, filter.toId())
            }.map { post ->
                val artist = artistCollection.find(post.artistId)!!
                val avatar = artist.avatarId?.let { avatarCollection.find(it) }
                val preview = previewCollection.findByPost(post.id)
                FeedPostResponse(
                    id = post.id,
                    subtitle = post.subtitle,
                    title = post.title,
                    description = post.description,
                    date = post.date,
                    artist = ArtistResponse(
                        id = artist.id,
                        followed = false,
                        follows = followerCollection.follows(artist.id).count(),
                        followers = followerCollection.followers(artist.id).count(),
                        email = artist.email,
                        name = artist.name,
                        avatar = avatar?.let { AvatarResponse(it.link) }
                    ),
                    likes = likeCollection.countByPost(post.id),
                    bookmarks = bookmarkCollection.countByPost(post.id),
                    comments = commentCollection.countByPost(post.id),
                    preview = preview?.link,
                    categories = categoryCollection.find(categoryInPostCollection.findByPost(post.id)).map {
                        CategoryResponse(
                            name = it.name
                        )
                    },
                    tags = tagCollection.find(tagInPostCollection.findByPost(post.id)).map {
                        TagResponse(
                            name = it.name
                        )
                    }
                )
            }
        }
    }

    override suspend fun like(id: String, artistId: String, initial: Boolean) = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(id.toId()) ?: throw ApiException(
                statusCode = HttpStatusCode.BadRequest,
                errorMessage = "Unable to bookmark nonexistent post"
            )
            val liked = likeCollection.liked(post.id, artistId.toId())
            if (initial) {
                if (liked) throw Exception("Already liked")
                likeCollection.insert(
                    Like(
                        postId = id.toId(),
                        artistId = artistId.toId()
                    )
                )
            } else {
                if (!liked) return@withContext
                likeCollection.deleteByArtist(post.id, artistId.toId())
            }
        }
    }

    override suspend fun bookmark(id: String, artistId: String, initial: Boolean) = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(id.toId()) ?: throw ApiException(
                statusCode = HttpStatusCode.BadRequest,
                errorMessage = "Unable to bookmark nonexistent post"
            )
            val bookmarked = bookmarkCollection.bookmarked(post.id, artistId.toId())
            if (initial) {
                if (bookmarked) throw Exception("Already bookmarked")
                bookmarkCollection.insert(
                    Bookmark(
                        postId = id.toId(),
                        artistId = artistId.toId()
                    )
                )
            } else {
                if (!bookmarked) return@withContext
                bookmarkCollection.deleteByArtist(post.id, artistId.toId())
            }
        }
    }

    override suspend fun categories(): List<String> = coroutineScope {
        withContext(Dispatchers.IO) {
            categoryCollection.all().map { it.name }
        }
    }
}