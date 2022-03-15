package com.app.gifvfeed.data.network.entity

import com.google.gson.annotations.SerializedName

open class AttachDto(
    val uuid: String,
    val type: Type,
    val data: AttachImageDto?=null,
    val image: AttachDto?=null
){
    enum class Type{
        @SerializedName("image")
        IMAGE
    }
}