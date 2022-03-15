package com.app.gifvfeed.data.network.entity

class InstagramEntryBlockDto(
    override val type: Type,
    val data: InstagramWrapper
): EntryBlockBase() {

    data class InstagramWrapper(
        val instagram: InstagramDataWrapper
    )

    data class InstagramDataWrapper(
        val data: InstagramBoxWrapper
    )

    data class InstagramBoxWrapper(
        val box_data: InstagramBoxData
    )

    data class InstagramBoxData(
        val url: String
    )

}