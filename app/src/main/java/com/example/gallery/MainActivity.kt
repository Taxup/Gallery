package com.example.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gallery.data.Api
import com.example.gallery.data.ApiImpl
import com.example.gallery.domain.feed.FeedInteractor
import com.example.gallery.ui.feed.FeedScreen
import com.example.gallery.ui.feed.FeedViewModel
import com.example.gallery.ui.theme.GalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = ApiImpl()
        val interactor: FeedInteractor = FeedInteractor.create(api)
        val feedViewModel by viewModels<FeedViewModel> { FeedViewModel.Factory(interactor) }
        setContent {
            GalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    val state by feedViewModel.state.collectAsState()
                    FeedScreen(state = state, performIntent = feedViewModel::performIntent)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GalleryTheme {
        Greeting("Android")
    }
}