package com.app.gifvfeed.data.network.entity

import com.google.gson.annotations.SerializedName

class AttachImageDto(
    val uuid: String,
    val width: Int,
    val height: Int,
    val color: String,

    @SerializedName("type")
    private val _type: Type?
) {
    val type get() = _type ?: Type.NOT_PLAYABLE

    enum class Type {
        @SerializedName("gif")
        PLAYABLE,

        NOT_PLAYABLE
    }
}