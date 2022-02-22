package com.app.gifvfeed.network.entity

class VideoEntryBlock(
    override val type: Type,
    val thumbnail: AttachImage,
    val external_service: ExternalService
) : EntryBlockBase()