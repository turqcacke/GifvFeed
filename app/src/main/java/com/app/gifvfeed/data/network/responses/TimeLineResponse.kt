package com.app.gifvfeed.data.network.responses

import com.app.gifvfeed.data.network.entity.TimeLineItemDto

data class TimeLineResponse (
    val message: String,
    val result: Result
){

    class Result(
        val items: List<TimeLineItemDto<out Any>>,
        val lastId: Int,
        val lastSortingValue: Int
    )
}