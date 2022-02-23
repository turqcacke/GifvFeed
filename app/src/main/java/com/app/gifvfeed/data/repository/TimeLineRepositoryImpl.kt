package com.app.gifvfeed.data.repository

import com.app.gifvfeed.data.datasource.TimeLineDataSource
import com.app.gifvfeed.data.mappers.TimeLineItemMapper
import com.app.gifvfeed.data.network.entity.TimeLineItemDto
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.app.gifvfeed.domain.repository.TimeLineRepository
import com.app.gifvfeed.data.mappers.base.ListedDto2DomainMapper
import javax.inject.Inject

class TimeLineRepositoryImpl @Inject constructor(
    private val retrofitSource: TimeLineDataSource
) : TimeLineRepository {

    private val timeLineMapper: ListedDto2DomainMapper<TimeLineItemDto<out Any>, TimeLineItem> =
        TimeLineItemMapper()

    override suspend fun getTimeline(): List<TimeLineItem> {
        val timeLineResponse = retrofitSource.getTimeline()
        val listedEntity = timeLineMapper.toListedEntity(timeLineResponse.result.items)
        return timeLineMapper.toListedEntity(timeLineResponse.result.items)
    }
}