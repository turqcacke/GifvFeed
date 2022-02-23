package com.app.gifvfeed.data.network.entity

import com.google.gson.annotations.SerializedName

data class TimeLineSubItemDto(
    val type: Type,
    ){
    enum class Type{
        @SerializedName("vacancy")
        VACANCY,

        @SerializedName("event")
        EVENT
    }
}