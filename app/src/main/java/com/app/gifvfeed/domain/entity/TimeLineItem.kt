package com.app.gifvfeed.domain.entity

sealed class TimeLineItem{
    class TimeLineEntry(val data: Entry): TimeLineItem()
}