package com.example.gallery.ui.feed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gallery.domain.feed.Feed
import com.example.gallery.ui.feed.mvi.FeedState
import com.example.gallery.ui.feed.mvi.FeedIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(state: FeedState, performIntent: (FeedIntent) -> Unit) {
    TextField(
        value = state.searchText,
        onValueChange = {
            performIntent.invoke(FeedIntent.Search(it))
        },
        modifier = Modifier.fillMaxWidth()
    )
    LazyColumn {

    }
}

@Composable
fun FeedItem(feed: Feed) {

}