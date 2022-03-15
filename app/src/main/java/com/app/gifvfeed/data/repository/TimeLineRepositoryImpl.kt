package com.app.gifvfeed.data.repository

import android.util.Log
import com.app.gifvfeed.data.datasource.TimeLineDataSource
import com.app.gifvfeed.data.mappers.TimeLineItemMapper
import com.app.gifvfeed.data.network.entity.TimeLineItemDto
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.app.gifvfeed.domain.repository.TimeLineRepository
import com.app.gifvfeed.data.mappers.base.ListedDto2DomainMapper
import com.app.gifvfeed.domain.entity.SetParams
import javax.inject.Inject

class TimeLineRepositoryImpl @Inject constructor(
    private val retrofitSource: TimeLineDataSource
) : TimeLineRepository {

    private val timeLineMapper: ListedDto2DomainMapper<TimeLineItemDto<out Any>, TimeLineItem> =
        TimeLineItemMapper()

    override suspend fun getTimeline(
        lastId: Int?,
        lastSortingValue: Int?
    ): Pair<List<TimeLineItem>, SetParams> {
        val timeLineResponse = retrofitSource.getTimeline(
            lastId = lastId,
            lastSortingValue = lastSortingValue
        )
        return Pair(
            timeLineMapper.toListedEntity(timeLineResponse.result.items),
            SetParams(
                lastId = timeLineResponse.result.lastId,
                lastSortingValue = timeLineResponse.result.lastSortingValue
            )
        )
    }
}