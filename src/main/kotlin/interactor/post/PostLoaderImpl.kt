package interactor.post

import database.collection.*
import database.document.CategoryInPost
import database.document.Post
import database.document.TagInPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import request.PostRequest
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
    override suspend fun upload(fields: PostRequest) {

        val post = Post(
            date = Date().time,
            artistId = fields.artist.toId(),
            description = fields.description,
            title = fields.title,
            subtitle = fields.subtitle,
            previewId = fields.preview.toId()
        )

        postCollection.insert(post)
        val categories = mutableListOf<CategoryInPost>()
        for (category in fields.categories)
            categories.add(
                CategoryInPost(
                    postId = post.id,
                    categoryId = category.toId()
                )
            )
        categoryInPostCollection.insert(categories)

        val tags = mutableListOf<TagInPost>()
        for (tag in fields.tags)
            tags.add(
                TagInPost(
                    postId = post.id,
                    tagId = tag.toId()
                )
            )
        tagInPostCollection.insert(tags)
    }

    override suspend fun post(id: String): PostResponse? = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(id.toId()) ?: return@withContext null
            val artist = artistCollection.find(post.artistId)!!
            val avatar = avatarCollection.find(artist.avatarId)!!
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
                    permalink = artist.permalink,
                    email = artist.email,
                    avatar = AvatarResponse(avatar.link)
                ),
                arts = arts.map { ArtResponse(it.name, it.link) },
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
                val avatar = avatarCollection.find(artist.avatarId)!!
                val bookmarks = bookmarkCollection.all().toList()
                val comment = commentCollection.getAllByPost(post.id.toString()).toList().map {
                    val author = artistCollection.find(it.artistId)!!
                    val avatar = avatarCollection.find(author.avatarId)!!
                    CommentResponse(
                        author = AuthorResponse(
                            name = author.name,
                            permalink = author.permalink,
                            avatar = AvatarResponse(link = avatar.link)
                        ),
                        date = it.date,
                        content = it.content
                    )
                }
                val preview = previewCollection.find(post.previewId)!!
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
                        permalink = artist.permalink,
                        avatar = AvatarResponse(link = avatar.link)
                    ),
                    bookmarks = bookmarks,
                    comments = comment,
                    preview = ArtResponse(
                        name = preview.name,
                        link = preview.link
                    )
                )
            }
        }
    }
}