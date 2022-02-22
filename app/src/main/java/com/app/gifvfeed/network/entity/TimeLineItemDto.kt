package com.app.gifvfeed.network.entity
import com.google.gson.annotations.SerializedName


open class TimeLineItemDto<T>(
    val type: Type? = null,
    val data: T? = null
){

    enum class Type {
        @SerializedName("news")
        NEWS,
        @SerializedName("entry")
        ENTRY,
        @SerializedName("vacancies")
        VACANCIES,
        @SerializedName("events")
        EVENTS,
        @SerializedName("onboarding")
        ONBOARDING,
        @SerializedName("flash")
        FLASH,
        @SerializedName("rating")
        RATING;

        override fun toString(): String {
            return super.toString().lowercase()
        }
    }
}

class TimeLineItemListedDto: TimeLineItemDto<List<TimeLineSubItem>>()
class TimeLineItemEntryDto: TimeLineItemDto<Entry>()



