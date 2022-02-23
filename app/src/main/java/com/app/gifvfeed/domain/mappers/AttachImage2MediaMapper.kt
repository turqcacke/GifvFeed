package com.app.gifvfeed.domain.mappers

import com.app.gifvfeed.data.network.entity.AttachImageDto
import com.app.gifvfeed.domain.entity.Media
import com.app.gifvfeed.domain.utils.Dto2DomainMapper

class AttachImage2MediaMapper: Dto2DomainMapper<AttachImageDto, Media> {
    override fun toEntity(dtoObj: AttachImageDto): Media {
        return Media(
            uuid = dtoObj.uuid
        )
    }
}