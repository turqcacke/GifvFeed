package com.app.gifvfeed.data.network.entity

import com.google.gson.annotations.SerializedName

data class EntryDto(
    val id: Int,
    val author: SubsiteDto,
    val title: String,
    val subsite: SubsiteDto,
    val type: Int,
    val counters: CountersDto,
    val likes: LikesDto,

    @SerializedName("blocks")
    val _blocks: List<EntryBlockBase?>
) {
    val blocks get() = _blocks.filterNotNull()

}