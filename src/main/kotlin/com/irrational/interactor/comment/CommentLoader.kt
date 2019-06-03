package com.irrational.interactor.comment

import com.irrational.di.AppComponent
import com.irrational.response.CommentResponse

interface CommentLoader {
    suspend fun comments(postId: String): List<CommentResponse>

    suspend fun insert(postId: String, artistId: String, content: String): CommentResponse

    companion object : CommentLoader by INSTANCE
}

private val INSTANCE = AppComponent.commentLoader()