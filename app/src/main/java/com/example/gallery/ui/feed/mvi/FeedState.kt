package com.example.gallery.ui.feed.mvi

import com.example.gallery.domain.feed.Feed

data class FeedState(
    val feeds: List<Feed> = emptyList(),
    val searchText: String = "",
    val isLoading: Boolean = false
)
