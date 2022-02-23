package com.app.gifvfeed.data.mappers.base

interface Dto2DomainMapper<DTO, DomainModel> {
    fun toEntity(dtoObj: DTO): DomainModel?
}