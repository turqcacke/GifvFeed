package com.app.gifvfeed.data.datasource

import com.app.gifvfeed.data.network.responses.TimeLineResponse
import com.app.gifvfeed.data.network.retrofit.ApiService
import javax.inject.Inject

class TimeLineRetrofitSource @Inject constructor(
    private val apiService: ApiService
) : TimeLineDataSource {
    override suspend fun getTimeline(
        subsiteIds: String?, lastId: Int?,
        lastSortingValue: Int?
    ): TimeLineResponse {
        subsiteIds?.let {
            return apiService.timeline(subsitesIds = it)
        }
        return apiService.timeline(lastId = lastId, lastSortingValue = lastSortingValue)
    }
}