package com.app.gifvfeed.data.network.entity

import com.google.gson.annotations.SerializedName

abstract class EntryBlockBase{
    abstract val type: Type
    enum class Type{
        @SerializedName("header")
        HEADER,
        @SerializedName("text")
        TEXT,
        @SerializedName("media")
        MEDIA,
        @SerializedName("video")
        VIDEO,
        @SerializedName("instagram")
        INSTAGRAM;

        override fun toString(): String {
            return super.toString().lowercase()
        }
    }
}

