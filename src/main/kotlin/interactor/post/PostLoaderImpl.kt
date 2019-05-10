package interactor.post

import database.collection.*
import database.document.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import response.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLoaderImpl @Inject constructor(
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
    private val bookmarkCollection: BookmarkCollection
) : PostLoader {

    override suspend fun insert(post: Post, categories: List<String>, tags: List<String>) = coroutineScope {
        withContext(Dispatchers.IO) {
            post.also {
                postCollection.insert(it)
                tagInPostCollection.insert(tags.map { tagName ->
                    val tag = Tag(tagName)
                    if (!tagCollection.contains(tag)) {
                        tagCollection.insert(tag)
                    }
                    TagInPost(it.id, tag.id)
                })
                categoryInPostCollection.insert(categories.map { categoryName ->
                    val category = Category(categoryName)
                    CategoryInPost(it.id, category.id)
                })
            }.let { it.id.toString() }
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
                    followed = false,
                    email = artist.email,
                    avatar = avatar?.let { AvatarResponse(it.link) }
                ),
                arts = arts.map { ArtResponse(it.link) },
                title = post.title,
                subtitle = post.subtitle,
                description = post.description,
                likes = likeCollection.countByPost(id),
                bookmarks = bookmarkCollection.countByPost(id),
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
                else -> postCollection.byArtistWithBoundary(from, to, filter.toId())
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

    override suspend fun like(id: String, artistId: String, initial: Boolean) = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(id.toId())
            if (post == null) {
                throw Exception("Unable to like nonexistent post")
            } else {
                val liked = likeCollection.liked(artistId.toId())
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
                    likeCollection.deleteByArtist(artistId.toId())
                }
            }
        }
    }

    override suspend fun bookmark(id: String, artistId: String, initial: Boolean) = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(id.toId())
            if (post == null) {
                throw Exception("Unable to bookmark nonexistent post")
            } else {
                val bookmarked = bookmarkCollection.bookmarked(artistId.toId())
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
                    bookmarkCollection.deleteByArtist(artistId.toId())
                }
            }
        }
    }
}