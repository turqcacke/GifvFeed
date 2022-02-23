package com.app.gifvfeed.data.datasource

import com.app.gifvfeed.data.network.responses.TimeLineResponse

interface TimeLineDataSource {
    suspend fun getTimeline(subsiteIds: String? = null): TimeLineResponse
}