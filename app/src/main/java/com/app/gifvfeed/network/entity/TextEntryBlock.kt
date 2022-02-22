package com.app.gifvfeed.network.entity

class TextEntryBlock(
    override val type: Type,
    val data: TextItem
): EntryBlockBase(){
    class TextItem(
        val text: String,
        val text_truncated: String
    )
}