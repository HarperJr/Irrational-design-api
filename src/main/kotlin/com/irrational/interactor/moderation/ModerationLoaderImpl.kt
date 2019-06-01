package com.irrational.interactor.moderation

import com.irrational.database.collection.ComplaintCollection
import com.irrational.database.collection.PostCollection
import com.irrational.database.collection.RoleCollection
import com.irrational.database.document.*
import com.irrational.response.StatusResponse
import com.irrational.response.SuccessResponse
import com.irrational.utils.ApiException
import io.ktor.http.HttpStatusCode
import org.litote.kmongo.Id
import javax.inject.Inject

class ModerationLoaderImpl @Inject constructor(
    private val complaintCollection: ComplaintCollection,
    private val postCollection: PostCollection,
    private val roleCollection: RoleCollection
) : ModerationLoader {

    override suspend fun moderatorCheck(roleId: Id<Role>): Boolean {
        val role = roleCollection.find(roleId) ?: throw ApiException(
            statusCode = HttpStatusCode.Forbidden,
            errorMessage = "role not found"
        )
        if (role.type == RoleType.MODERATOR) return true
        return false
    }

    override suspend fun complaints(): List<Complaint> {
        return complaintCollection.all()
    }

    override suspend fun pushComplaint(
        artistId: Id<Artist>, postId: Id<Post>
    ): StatusResponse {

        val complaint = Complaint(
            post = postId,
            initiator = artistId
        )
        complaintCollection.insert(complaint)
        return SuccessResponse(
            message = "Complaint successfully pushed"
        )
    }

    override suspend fun acceptComplaint(complaintId: Id<Complaint>): StatusResponse {
        val complaint = complaintCollection.find(complaintId) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "complaint doesn't exist"
        )
        val post = postCollection.find(complaint.post) ?: throw ApiException(
            statusCode = HttpStatusCode.ExpectationFailed,
            errorMessage = "post not found"
        )
        post.blocked = true
        postCollection.update(post)
        complaintCollection.delete(complaint.id)
        return SuccessResponse(
            message = "Complaint accepted"
        )
    }

    override suspend fun rejectComplaint(complaintId: Id<Complaint>): StatusResponse {

        val complaint = complaintCollection.find(complaintId) ?: throw ApiException(
            statusCode = HttpStatusCode.BadRequest,
            errorMessage = "complaint doesn't exist"
        )
        complaintCollection.delete(complaint.id)
        return SuccessResponse(
            message = "Complaint rejected"
        )
    }

    override suspend fun blockPost(postId: Id<Post>): StatusResponse {
        val post = postCollection.find(postId) ?: throw ApiException(
            statusCode = HttpStatusCode.ExpectationFailed,
            errorMessage = "post not found"
        )

        post.blocked = true
        postCollection.update(post)
        return SuccessResponse(
            message = "post blocked"
        )
    }
}