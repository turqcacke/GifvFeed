package com.app.gifvfeed.presentation.ui.feed.components

import com.app.gifvfeed.domain.entity.TimeLineItem

sealed class FeedState{
    object InitLoading : FeedState()
    class Success(val data: List<TimeLineItem>): FeedState()
    class Error(val errorMessage: String): FeedState()
}