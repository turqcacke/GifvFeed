package com.app.gifvfeed.domain.mappers

import com.app.gifvfeed.data.network.entity.EntryDto
import com.app.gifvfeed.data.network.entity.TimeLineItemDto
import com.app.gifvfeed.data.network.entity.TimeLineItemEntryDto
import com.app.gifvfeed.domain.entity.Entry
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.app.gifvfeed.domain.utils.Dto2DomainMapper

class TimeLineItemMapper : Dto2DomainMapper<TimeLineItemDto<out Any>, TimeLineItem> {

    private val entryMapper: Dto2DomainMapper<EntryDto, Entry> = EntryMapper()

    override fun toEntity(dtoObj: TimeLineItemDto<out Any>): TimeLineItem? {
        val timeLineObject = when (dtoObj) {
            is TimeLineItemEntryDto -> {
                var timeLineItem: TimeLineItem? = null
                dtoObj.data?.let { entryDto ->
                    entryMapper.toEntity(entryDto)?.let { entry ->
                        timeLineItem = TimeLineItem.TimeLineEntry(
                            entry
                        )
                    }
                }
                timeLineItem
            }
            else -> {
                null
            }
        }
        return timeLineObject
    }

}