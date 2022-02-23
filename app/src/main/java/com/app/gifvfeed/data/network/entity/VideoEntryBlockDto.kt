package com.app.gifvfeed.data.network.entity

class VideoEntryBlockDto(
    override val type: Type,
    val thumbnail: AttachImageDto,
    val external_service: ExternalServiceDto
) : EntryBlockBase()