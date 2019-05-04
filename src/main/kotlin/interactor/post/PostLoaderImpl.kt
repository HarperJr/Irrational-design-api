package interactor.post

import database.collection.*
import database.document.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import response.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLoaderImpl @Inject constructor(
    private val postCollection: PostCollection,
    private val bookmarkCollection: BookmarkCollection,
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection,
    private val artCollection: ArtCollection,
    private val tagCollection: TagCollection,
    private val categoryCollection: CategoryCollection,
    private val tagInPostCollection: TagInPostCollection,
    private val categoryInPostCollection: CategoryInPostCollection,
    private val commentCollection: CommentCollection,
    private val previewCollection: PreviewCollection
) : PostLoader {

    override suspend fun insert(
        artistId: String, title: String, subtitle: String, description: String,
        categories: List<String>, tags: List<String>
    ) = coroutineScope {
        withContext(Dispatchers.IO) {
            postCollection.insert(
                Post(
                    artistId = artistId.toId(),
                    title = title,
                    subtitle = subtitle,
                    description = description,
                    date = Date().time
                )
            )
        }
    }

    override suspend fun post(id: String): PostResponse? = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(id.toId()) ?: return@withContext null
            val artist = artistCollection.find(post.artistId)!!
            val avatar = artist.avatarId?.let { avatarCollection.find(it) }
            val arts = artCollection.findByPost(post.id)
            val tags = let {
                val tagsInPost = tagInPostCollection.findByPost(post.id)
                tagCollection.find(tagsInPost)
            }
            val categories = let {
                val categoriesInPost = categoryInPostCollection.findByPost(post.id)
                categoryCollection.find(categoriesInPost)
            }
            return@withContext PostResponse(
                id = post.id,
                artist = ArtistResponse(
                    id = artist.id,
                    name = artist.name,
                    followed = false, //todo
                    email = artist.email,
                    avatar = avatar?.let { AvatarResponse(it.link) }
                ),
                arts = arts.map { ArtResponse(it.link) },
                title = post.title,
                subtitle = post.subtitle,
                description = post.description,
                likes = 0,
                bookmarks = 0,
                comments = 0,
                tags = tags.map { TagResponse(it.name) },
                categories = categories.map { CategoryResponse(it.name) },
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
                else -> emptyList()
            }.map { post ->
                val artist = artistCollection.find(post.artistId)!!
                val avatar = artist.avatarId?.let { avatarCollection.find(it) }
                val bookmarks = bookmarkCollection.all().toList()
                val comment = commentCollection.getAllByPost(post.id.toString()).toList().map {
                    val author = artistCollection.find(it.artistId)!!
                    val authorAvatar = author.avatarId?.let { avatarCollection.find(it) }
                    CommentResponse(
                        author = AuthorResponse(
                            name = author.name,
                            avatar = authorAvatar?.let { AvatarResponse(it.link) }
                        ),
                        date = it.date,
                        content = it.content
                    )
                }
                val preview = previewCollection.findByPost(post.id)!!
                FeedPostResponse(
                    id = post.id,
                    subtitle = post.subtitle,
                    title = post.title,
                    date = post.date,
                    artist = ArtistResponse(
                        id = artist.id,
                        followed = false,
                        email = artist.email,
                        name = artist.name,
                        avatar = avatar?.let { AvatarResponse(it.link) }
                    ),
                    bookmarks = bookmarks,
                    comments = comment,
                    preview = ArtResponse(
                        link = preview.link
                    )
                )
            }
        }
    }
}