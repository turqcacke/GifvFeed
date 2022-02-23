package com.app.gifvfeed.domain.mappers

import com.app.gifvfeed.data.network.entity.*
import com.app.gifvfeed.domain.entity.EntryBlock
import com.app.gifvfeed.domain.entity.ExternalVideoRes
import com.app.gifvfeed.domain.entity.Media
import com.app.gifvfeed.domain.entity.Video
import com.app.gifvfeed.domain.utils.Dto2DomainMapper
import com.app.gifvfeed.domain.utils.MixedDto2DomainMapper

class EntryBlockMapper : MixedDto2DomainMapper<EntryBlockBase, EntryBlock> {

    private val attach2MediaMapper: Dto2DomainMapper<AttachDto, Media> = AttachDto2MediaMapper()
    private val attachImage2MediaMapper: Dto2DomainMapper<AttachImageDto, Media> =
        AttachImage2MediaMapper()
    private val externalService2VideoRes: Dto2DomainMapper<ExternalServiceDto, ExternalVideoRes> =
        ExternalVideoResMapper()

    override fun toEntity(dtoObj: EntryBlockBase): EntryBlock? {
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
            is VideoEntryBlockDto -> {
                val externalVideoRes: ExternalVideoRes? =
                    externalService2VideoRes.toEntity(dtoObj.external_service)
                val thumbnail: Media? = attachImage2MediaMapper.toEntity(dtoObj.thumbnail)

                if (externalVideoRes !== null && thumbnail !== null) {
                    return EntryBlock.VideoBlock(
                        Video(
                            externalVideoRes,
                            thumbnail
                        )
                    )
                }
                null
            }
            else -> {
                null
            }
        }
    }

    override fun toListedEntity(listDtoObj: List<EntryBlockBase>): List<EntryBlock> {
        TODO("Not yet implemented")
    }
}