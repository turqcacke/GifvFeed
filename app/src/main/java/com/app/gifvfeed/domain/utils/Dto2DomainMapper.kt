package com.app.gifvfeed.domain.utils

interface Dto2DomainMapper<DTO, DomainModel> {
    fun toEntity(dtoObj: DTO): DomainModel?
}