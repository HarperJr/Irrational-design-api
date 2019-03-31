package interactor.post

import database.collection.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import response.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLoaderImpl @Inject constructor(
    private val postCollection: PostCollection,
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection,
    private val artCollection: ArtCollection,
    private val previewCollection: PreviewCollection,
    private val tagCollection: TagCollection,
    private val categoryCollection: CategoryCollection,
    private val tagInPostCollection: TagInPostCollection,
    private val categoryInPostCollection: CategoryInPostCollection
) : PostLoader {

    override suspend fun post(id: String): PostResponse? = coroutineScope {
        withContext(Dispatchers.IO) {
            val post = postCollection.find(id) ?: return@withContext null
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
            postCollection.allWithBoundary(from, to)
                .map {
                    val artist = artistCollection.find(it.artistId)!!
                    val avatar = avatarCollection.find(artist.avatarId)!!
                    val preview = previewCollection.find(it.previewId)!!
                    FeedPostResponse(
                        id = it.id,
                        preview = ArtResponse(
                            name = preview.name,
                            link = preview.link
                        ),
                        artist = ArtistResponse(
                            id = artist.id,
                            name = artist.name,
                            followed = false,
                            permalink = artist.permalink,
                            email = artist.email,
                            avatar = AvatarResponse(avatar.link)
                        ),
                        title = it.title,
                        subtitle = it.subtitle,
                        likes = 0,
                        bookmarks = 0,
                        comments = 0,
                        date = it.date
                    )
                }
        }
    }
}