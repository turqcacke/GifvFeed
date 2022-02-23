package com.app.gifvfeed.domain.utils

interface ListedDto2DomainMapper<DTO, DomainModel> {
    fun toListedEntity(listDtoObj: List<DTO>): List<DomainModel>
}