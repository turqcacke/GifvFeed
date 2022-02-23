package com.app.gifvfeed.data.network.entity

class VideoEntryBlockDto(
    override val type: Type,
    val data: VideoDtoWrapper
) : EntryBlockBase(){

    data class VideoDtoWrapper(
        val video: VideoDtoDataWrapper
    )

    data class VideoDtoDataWrapper(
        val data: VideoDto
    )

    data class VideoDto(
        val thumbnail: AttachDto,
        val external_service: ExternalServiceDto
    )
}