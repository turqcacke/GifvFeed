package com.app.gifvfeed.data.network.entity

data class EntryDto (
    val id: Int,
    val author: SubsiteDto,
    val subsite: SubsiteDto,
    val type: Int,
    val counters: CountersDto,
    val blocks: List<EntryBlockBase>
)