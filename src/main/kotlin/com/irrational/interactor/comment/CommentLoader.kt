package com.irrational.interactor.comment

import com.irrational.database.document.Post
import com.irrational.di.AppComponent
import org.litote.kmongo.Id
import com.irrational.response.CommentResponse

interface CommentLoader {
    suspend fun comments(id: String): List<CommentResponse>

    suspend fun insert(postId: Id<Post>, author: String, content: String)

    companion object : CommentLoader by INSTANCE
}

private val INSTANCE = AppComponent.commentLoader()