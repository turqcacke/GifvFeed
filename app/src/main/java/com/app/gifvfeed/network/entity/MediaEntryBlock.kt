package com.app.gifvfeed.network.entity

import com.app.gifvfeed.network.entity.utils.ItemListed

class MediaEntryBlock(
    override val type: Type,
    val data: ItemListed<Attach>
): EntryBlockBase()