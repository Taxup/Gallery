package com.example.gallery.domain.feed

import com.example.gallery.data.Api

interface FeedInteractor {

    companion object Factory {
        fun create(api: Api): FeedInteractor = FeedInteractorImpl(api)
    }

    suspend fun getFeed(searchText: String): List<Feed>
}