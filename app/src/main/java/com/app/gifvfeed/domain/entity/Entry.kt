package com.app.gifvfeed.domain.entity

data class Entry(
    val subsite: Subsite,
    val author: Subsite,
    val title: String,
    val blocks: List<EntryBlock>,
    val counter: Counter,
    val mediaBlocks: List<EntryBlock.MediaBlock> = blocks.filterIsInstance<EntryBlock.MediaBlock>(),
    val textBlocks: List<EntryBlock.TextBlock> = blocks.filterIsInstance<EntryBlock.TextBlock>(),
    val linkBlocks: List<EntryBlock> = blocks.filter {
        it is EntryBlock.YouTubeVideoBlock || it is EntryBlock.InstagramBlock
    }
)