package interactor.post

import FileManager
import FileManager.ARTS_PATH
import database.collection.*
import database.document.*
import database.transaction.Transaction
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import response.*
import utils.ApiException
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

    override suspend fun upload(
        post: Post, categories: List<String>,
        tags: List<String>, images: List<ByteArray>
    ) = coroutineScope {
        withContext(Dispatchers.IO) {
            val arts = images.mapIndexed { index, image ->
                val path = "/${post.title}/$index" //path will be like /arts/{post_name}/0..n
                FileManager.save(ARTS_PATH.resolve(path), image)
                Art(post.id, path)
            }
            val categoriesInPost = categories.map {
                val category = categoryCollection.findByName(it)
                    ?: throw Exception("Unknown category") //Categories have to be present in database
                CategoryInPost(post.id, category.id)
            }
            val tagsInPost = tags.map {
                val tag = tagCollection.findByName(it)
                    ?: Tag(it).also { tag -> tagCollection.insert(tag) }
                TagInPost(post.id, tag.id)
            }
            postCollection.insert(post)
            categoryInPostCollection.insert(categoriesInPost)
            tagInPostCollection.insert(tagsInPost)
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
                        email = artist.email,
                        name = artist.name,
                        avatar = avatar?.let { AvatarResponse(it.link) }
                    ),
                    likes = likeCollection.countByPost(post.id),
                    bookmarks = bookmarkCollection.countByPost(post.id),
                    comments = commentCollection.countByPost(post.id),
                    preview = preview?.link
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