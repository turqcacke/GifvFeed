package com.app.gifvfeed.data.mappers

import com.app.gifvfeed.data.network.entity.AttachImageDto
import com.app.gifvfeed.domain.entity.Media
import com.app.gifvfeed.data.mappers.base.Dto2DomainMapper

class AttachImage2MediaMapper : Dto2DomainMapper<AttachImageDto, Media> {
    override fun toEntity(dtoObj: AttachImageDto?): Media? {
        var mediaEntity: Media? = null
        dtoObj?.let {
            mediaEntity = Media(
                uuid = dtoObj.uuid,
                isPlayable = when (dtoObj.type.name) {
                    AttachImageDto.Type.PLAYABLE.name -> {
                        true
                    }
                    else -> false
                },
                color = dtoObj.color
            )
        }
        return mediaEntity
    }
}