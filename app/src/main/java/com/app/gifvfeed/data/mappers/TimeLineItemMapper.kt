package com.app.gifvfeed.data.mappers

import com.app.gifvfeed.data.network.entity.EntryDto
import com.app.gifvfeed.data.network.entity.TimeLineItemDto
import com.app.gifvfeed.data.network.entity.TimeLineItemEntryDto
import com.app.gifvfeed.domain.entity.Entry
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.app.gifvfeed.data.mappers.base.Dto2DomainMapper
import com.app.gifvfeed.data.mappers.base.MixedDto2DomainMapper
import javax.inject.Inject

class TimeLineItemMapper : MixedDto2DomainMapper<TimeLineItemDto<out Any>, TimeLineItem> {

    private val entryMapper: Dto2DomainMapper<EntryDto, Entry> = EntryMapper()

    override fun toEntity(dtoObj: TimeLineItemDto<out Any>?): TimeLineItem? {
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

    override fun toListedEntity(listDtoObj: List<TimeLineItemDto<out Any>>?): List<TimeLineItem> {
        val tempList: MutableList<TimeLineItem?> = mutableListOf()
        if (listDtoObj != null) {
            for (dto in listDtoObj) {
                toEntity(dto)?.let {
                    tempList.add(it)
                }
            }
        }
        return tempList.filterNotNull()
    }

}