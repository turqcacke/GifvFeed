package com.app.gifvfeed.network.entity

import com.google.gson.annotations.SerializedName

data class TimeLineSubItem(
    val type: Type,
    ){
    enum class Type{
        @SerializedName("vacancy")
        VACANCY,

        @SerializedName("event")
        EVENT
    }
}