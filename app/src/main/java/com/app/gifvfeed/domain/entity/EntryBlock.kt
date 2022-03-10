package com.app.gifvfeed.domain.entity

sealed class EntryBlock {

    enum class BlockType{
        TEXT,
        VIDEO,
        IMAGE
    }
    class MediaBlock(val data: Media) : EntryBlock()
    class TextBlock(val text: String, val text_truncated: String?) : EntryBlock()
    class YouTubeVideoBlock(val video: Video): EntryBlock()
    class InstagramBlock(val url: String): EntryBlock()
}