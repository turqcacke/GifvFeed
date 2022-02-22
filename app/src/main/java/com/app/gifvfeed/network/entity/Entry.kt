package com.app.gifvfeed.network.entity

data class Entry (
    val id: Int,
    val author: Subsite,
    val subsite: Subsite,
    val type: Int,
    val counters: Counters,
    val blocks: List<EntryBlockBase>
)