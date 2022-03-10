package com.app.gifvfeed.data.mappers.base

interface ListedDto2DomainMapper<DTO, DomainModel> {
    fun toListedEntity(listDtoObj: List<DTO>?): List<DomainModel>
}