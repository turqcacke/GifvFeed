package com.app.gifvfeed.domain.repository
import com.app.gifvfeed.domain.entity.TimeLineItem

interface TimeLineRepository {
    suspend fun getTimeline(): List<TimeLineItem>
}