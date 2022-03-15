package com.app.gifvfeed.data.mappers

import com.app.gifvfeed.data.network.entity.*
import com.app.gifvfeed.domain.entity.EntryBlock
import com.app.gifvfeed.domain.entity.ExternalVideoRes
import com.app.gifvfeed.domain.entity.Media
import com.app.gifvfeed.domain.entity.Video
import com.app.gifvfeed.data.mappers.base.Dto2DomainMapper
import com.app.gifvfeed.data.mappers.base.MixedDto2DomainMapper

class EntryBlockMapper : MixedDto2DomainMapper<EntryBlockBase, EntryBlock> {

    private val attach2MediaMapper: Dto2DomainMapper<AttachDto, Media> = AttachDto2MediaMapper()
    private val attachImage2MediaMapper: Dto2DomainMapper<AttachImageDto, Media> =
        AttachImage2MediaMapper()
    private val externalService2VideoRes: Dto2DomainMapper<ExternalServiceDto, ExternalVideoRes> =
        ExternalService2ExternalVideoResMapper()

    override fun toEntity(dtoObj: EntryBlockBase?): EntryBlock? {
        return when (dtoObj) {
            is MediaEntryBlockDto -> {
                var media: Media? = null

                dtoObj.data.items[0].image?.let {
                    media = attach2MediaMapper.toEntity(it)
                }
                media?.let {
                    return EntryBlock.MediaBlock(it)
                }
                null
            }
            is TextEntryBlockDto -> return EntryBlock.TextBlock(
                dtoObj.data.text,
                dtoObj.data.text_truncated
            )
            is YouTubeVideoEntryBlockDto -> {
                val externalVideoRes: ExternalVideoRes? =
                    externalService2VideoRes.toEntity(dtoObj.data.video.data.external_service)
                val thumbnail: Media? = dtoObj.data.video.data.thumbnail.data?.let {
                    attachImage2MediaMapper.toEntity(
                        it
                    )
                }

                if (externalVideoRes !== null && thumbnail !== null) {
                    return EntryBlock.YouTubeVideoBlock(
                        Video(
                            externalVideoRes,
                            thumbnail
                        )
                    )
                }
                null
            }
            is InstagramEntryBlockDto -> {
                return EntryBlock.InstagramBlock(
                    dtoObj.data.instagram.data.box_data.url
                )
            }
            else -> {
                null
            }
        }
    }

    override fun toListedEntity(listDtoObj: List<EntryBlockBase>?): List<EntryBlock> {
        val listEntryBlock: MutableList<EntryBlock?> = mutableListOf()
        if (listDtoObj != null) {
            for(dto in listDtoObj){
                toEntity(dto)?.let{
                    listEntryBlock.add(it)
                }
            }
        }
        return listEntryBlock.filterNotNull()
    }
}