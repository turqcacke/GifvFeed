package com.app.gifvfeed.data.mappers

import com.app.gifvfeed.data.network.entity.EntryBlockBase
import com.app.gifvfeed.data.network.entity.EntryDto
import com.app.gifvfeed.data.network.entity.SubsiteDto
import com.app.gifvfeed.domain.entity.Entry
import com.app.gifvfeed.domain.entity.EntryBlock
import com.app.gifvfeed.domain.entity.Subsite
import com.app.gifvfeed.data.mappers.base.Dto2DomainMapper
import com.app.gifvfeed.data.mappers.base.ListedDto2DomainMapper
import javax.inject.Inject

class EntryMapper : Dto2DomainMapper<EntryDto, Entry> {

    private val blockMapper: ListedDto2DomainMapper<EntryBlockBase, EntryBlock> = EntryBlockMapper()
    private val subsiteMapper: Dto2DomainMapper<SubsiteDto, Subsite> = SubsiteMapper()

    override fun toEntity(dtoObj: EntryDto): Entry? {
        val author = subsiteMapper.toEntity(dtoObj.author)
        val subsite = subsiteMapper.toEntity(dtoObj.subsite)
        val blocks = blockMapper.toListedEntity(dtoObj.blocks)

        return when {
            author !== null && subsite !== null && blocks.isNotEmpty() -> Entry(
                author = author,
                subsite = subsite,
                blocks = blocks
            )
            else -> null
        }
    }
}