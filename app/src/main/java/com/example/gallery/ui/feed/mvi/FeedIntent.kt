package com.example.gallery.ui.feed.mvi

sealed class FeedIntent {
    class Search(val text: String): FeedIntent()
}
