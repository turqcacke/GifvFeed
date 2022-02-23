package com.app.gifvfeed.data.mappers

import com.app.gifvfeed.data.network.entity.AttachDto
import com.app.gifvfeed.data.network.entity.AttachImageDto
import com.app.gifvfeed.domain.entity.Media
import com.app.gifvfeed.data.mappers.base.Dto2DomainMapper
import javax.inject.Inject

class AttachDto2MediaMapper : Dto2DomainMapper<AttachDto, Media> {

    private val attachImage2Media: Dto2DomainMapper<AttachImageDto, Media> = AttachImage2MediaMapper()

    override fun toEntity(dtoObj: AttachDto): Media? {
        return when (dtoObj.data) {
            null -> null
            else -> attachImage2Media.toEntity(dtoObj.data)
        }
    }

}