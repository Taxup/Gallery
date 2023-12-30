package com.example.gallery.domain.feed

import com.example.gallery.data.Api
import com.example.gallery.data.models.FeedNetworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FeedInteractorImpl(private val api: Api) : FeedInteractor {

    override suspend fun getFeed(searchText: String): List<Feed> {
        val feed = withContext(Dispatchers.IO) { api.getFeed() }
        return feed.map { it.toSharedModel() }
    }

    private fun FeedNetworkModel.toSharedModel() = Feed(
        id = id,
        title = title,
        icon = icon
    )
}