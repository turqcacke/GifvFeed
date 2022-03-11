package com.app.gifvfeed.presentation.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.gifvfeed.presentation.ui.feed.components.FeedState
import com.app.gifvfeed.presentation.ui.feed.components.TimelineCardList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

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
            ) {
                CircularProgressIndicator(modifier = Modifier.wrapContentSize())
            }
        }

        is FeedState.Success -> {
            SwipeRefresh(
                state = rememberSwipeRefreshState(viewModel.isRefreshing.value),
                onRefresh = {
                    viewModel.refresh()
                },
            ) {
                TimelineCardList(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    list = state.data,
                    loadMoreState = viewModel.getLoadingState,
                    isRefreshingState = viewModel.isRefreshing
                ) {
                    viewModel.getTimeLineItems()
                }
            }
        }

        is FeedState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    text = state.errorMessage,
                    color = MaterialTheme.colors.secondary
                )

                Button(
                    modifier = Modifier.wrapContentSize(),
                    onClick = {
                        viewModel.retry()
                    }) {
                    Text(text = "Retry")
                }
            }
        }
    }
}