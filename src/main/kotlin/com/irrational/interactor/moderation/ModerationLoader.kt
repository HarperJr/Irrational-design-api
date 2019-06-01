package com.irrational.interactor.moderation

import com.irrational.database.document.Artist
import com.irrational.database.document.Complaint
import com.irrational.database.document.Post
import com.irrational.database.document.Role
import com.irrational.di.AppComponent
import com.irrational.response.StatusResponse
import org.litote.kmongo.Id

interface ModerationLoader {
    suspend fun pushComplaint(artistId: Id<Artist>, postId: Id<Post>): StatusResponse

    suspend fun acceptComplaint(complaintId: Id<Complaint>): StatusResponse

    suspend fun rejectComplaint(complaintId: Id<Complaint>): StatusResponse

    suspend fun complaints(): List<Complaint>

    suspend fun moderatorCheck(roleId: Id<Role>): Boolean

    suspend fun blockPost(postId: Id<Post>): StatusResponse

    companion object : ModerationLoader by INSTANCE
}

private val INSTANCE = AppComponent.moderationLoader()