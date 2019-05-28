package com.irrational.interactor.art

import com.irrational.database.document.Art
import com.irrational.di.AppComponent

interface ArtLoader {
    suspend fun insert(arts: List<Art>)

    companion object : ArtLoader by INSTANCE
}

private val INSTANCE = AppComponent.artLoader()