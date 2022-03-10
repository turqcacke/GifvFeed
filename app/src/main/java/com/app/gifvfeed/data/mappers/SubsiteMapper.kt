package com.app.gifvfeed.data.mappers

import com.app.gifvfeed.data.network.entity.EntryDto
import com.app.gifvfeed.data.network.entity.SubsiteDto
import com.app.gifvfeed.domain.entity.Entry
import com.app.gifvfeed.domain.entity.Subsite
import com.app.gifvfeed.data.mappers.base.Dto2DomainMapper
import com.app.gifvfeed.data.network.entity.AttachDto
import com.app.gifvfeed.domain.entity.Media
import javax.inject.Inject

class SubsiteMapper: Dto2DomainMapper<SubsiteDto, Subsite> {
    private val attachDto2MediaMapper: Dto2DomainMapper<AttachDto, Media> = AttachDto2MediaMapper()
    override fun toEntity(dtoObj: SubsiteDto?): Subsite? {
        val avatar =  attachDto2MediaMapper.toEntity(dtoObj?.avatar)
        dtoObj?.name?.let {
            if (avatar !==null) {
                return Subsite(
                    dtoObj.name,
                    avatar
                )
            }
        }
        return null
    }
}