package interactor.comment

import database.collection.ArtistCollection
import database.collection.AvatarCollection
import database.collection.CommentCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import response.AuthorResponse
import response.AvatarResponse
import response.CommentResponse
import java.util.*
import javax.inject.Inject

class CommentInteractorImpl @Inject constructor(
    private val commentCollection: CommentCollection,
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection
) : CommentInteractor {
    override suspend fun comments(id: String): List<CommentResponse> = coroutineScope {
        withContext(Dispatchers.IO) {
            commentCollection.getAllByPost(id)
                .map {
                    val author = artistCollection.find(it.artistId)!!
                    val avatar = avatarCollection.find(author.avatarId)!!
                    CommentResponse(
                        author = AuthorResponse(
                            name = author.name,
                            permalink = author.permalink,
                            avatar = AvatarResponse(
                                link = avatar.path
                            )
                        ),
                        content = it.content,
                        date = Date(it.date)
                    )
                }
        }
    }
}