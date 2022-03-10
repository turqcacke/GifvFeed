package com.app.gifvfeed.presentation.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.gifvfeed.presentation.ui.feed.components.FeedState
import com.app.gifvfeed.presentation.ui.feed.components.TimelineCardList

@Composable
fun FeedScreen(
    viewModel: FeedScreenViewModel = hiltViewModel()
) {
    when (val state = viewModel.feedItemsState.value) {
        is FeedState.InitLoading -> {
            viewModel.getTimeLineItems()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                CircularProgressIndicator(modifier = Modifier.wrapContentSize())
            }
        }

        is FeedState.Success -> {
            TimelineCardList(
                modifier = Modifier.fillMaxSize().background(Color.White),
                list = state.data,
                loadMoreState = viewModel.getLoadingState
            ) {
                viewModel.getTimeLineItems()
            }
        }

        is FeedState.Error -> {
            Text(text = state.errorMessage)
        }
    }
}