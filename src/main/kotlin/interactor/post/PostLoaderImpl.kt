package interactor.post

import database.collection.*
import database.document.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.Id
import org.litote.kmongo.toId
import request.PostRequest
import response.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class PostLoaderImpl @Inject constructor(
    private val postCollection: PostCollection,
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection,
    private val artCollection: ArtCollection,
    private val tagCollection: TagCollection,
    private val categoryCollection: CategoryCollection,
    private val tagInPostCollection: TagInPostCollection,
    private val categoryInPostCollection: CategoryInPostCollection
) : PostLoader {
    override suspend fun upload(fields: PostRequest) {

        val post = Post(
            date = Date().time,
            artistId = fields.artist,
            description = fields.description,
            title = fields.title,
            subtitle = fields.subtitle,
            previewId = fields.preview
        )

        postCollection.insert(post)
        val categories = mutableListOf<CategoryInPost>()
        for ( category in fields.categories )
        categories.add(CategoryInPost(
            postId = post.id,
            categoryId = category
        ))
        categoryInPostCollection.insert(categories)

        val tags = mutableListOf<TagInPost>()
        for ( tag in fields.tags )
            tags.add(
                TagInPost(
                postId = post.id,
                tagId = tag
            ))
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
            PostResponse(
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

    override suspend fun posts(from: Int, to: Int): List<FeedPostResponse> = coroutineScope {
        withContext(Dispatchers.IO) {
            emptyList<FeedPostResponse>()
        }
    }
}