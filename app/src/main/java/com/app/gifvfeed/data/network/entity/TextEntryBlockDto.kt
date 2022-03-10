package com.app.gifvfeed.data.network.entity

class TextEntryBlockDto(
    override val type: Type,
    val data: TextItem
): EntryBlockBase(){
    class TextItem(
        val text: String,
        val text_truncated: String? = null,
    )
}