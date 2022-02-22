package com.app.gifvfeed.network.entity

import com.google.gson.annotations.SerializedName
import java.util.*

open class Attach(
    val uuid: String,
    val type: Type,
    val data: AttachImage?=null,
    val image: Attach?=null
){
    enum class Type{
        @SerializedName("image")
        IMAGE
    }
}