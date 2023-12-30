package com.example.gallery.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gallery.domain.feed.FeedInteractor
import com.example.gallery.ui.feed.mvi.FeedState
import com.example.gallery.ui.feed.mvi.FeedIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class FeedViewModel(private val interactor: FeedInteractor) : ViewModel() {

    val state = MutableStateFlow(FeedState())

    private val searchFlow: Flow<String> = MutableStateFlow("")

    init {
        searchFlow
            .debounce(300)
            .onEach { search(it) }
            .launchIn(viewModelScope)
    }

    fun performIntent(intent: FeedIntent) {
        when (intent) {
            is FeedIntent.Search -> search(intent.text)
        }
    }

    private fun search(searchText: String) {
        viewModelScope.launch(Dispatchers.Default) {
            state.value = state.value.copy(isLoading = true)

            val feeds = interactor.getFeed(searchText)

            state.value = state.value.copy(
                feeds = feeds,
                isLoading = false
            )
        }
    }

    class Factory(private val interactor: FeedInteractor) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedViewModel(interactor) as T
        }
    }
}