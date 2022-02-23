package com.app.gifvfeed.domain.entity

data class Entry (
    val subsite: Subsite,
    val author: Subsite,
    val blocks: List<EntryBlock>
)