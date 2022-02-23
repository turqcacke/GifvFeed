package com.app.gifvfeed.domain.mappers

import com.app.gifvfeed.data.network.entity.EntryDto
import com.app.gifvfeed.data.network.entity.SubsiteDto
import com.app.gifvfeed.domain.entity.Entry
import com.app.gifvfeed.domain.entity.Subsite
import com.app.gifvfeed.domain.utils.Dto2DomainMapper
import javax.inject.Inject
import javax.inject.Singleton

class SubsiteMapper: Dto2DomainMapper<SubsiteDto, Subsite> {

    private lateinit var entryMapper: Dto2DomainMapper<EntryDto, Entry>

    override fun toEntity(dtoObj: SubsiteDto): Subsite {
        return Subsite(
            dtoObj.name,
            dtoObj.avatar.uuid
        )
    }
}