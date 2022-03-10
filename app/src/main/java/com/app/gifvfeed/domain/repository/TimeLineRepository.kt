package com.app.gifvfeed.domain.repository
import com.app.gifvfeed.domain.entity.SetParams
import com.app.gifvfeed.domain.entity.TimeLineItem

interface TimeLineRepository {
    suspend fun getTimeline(lastId: Int?, lastSortingValue: Int?): Pair<List<TimeLineItem>, SetParams>
}