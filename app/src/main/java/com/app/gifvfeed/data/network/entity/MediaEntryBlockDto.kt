package com.app.gifvfeed.data.network.entity

import com.app.gifvfeed.data.network.entity.utils.ItemListed

class MediaEntryBlockDto(
    override val type: Type,
    val data: ItemListed<AttachDto>
): EntryBlockBase()