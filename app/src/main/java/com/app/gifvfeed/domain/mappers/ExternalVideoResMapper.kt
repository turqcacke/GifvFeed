package com.app.gifvfeed.domain.mappers

import com.app.gifvfeed.data.network.entity.ExternalServiceDto
import com.app.gifvfeed.domain.entity.ExternalVideoRes
import com.app.gifvfeed.domain.utils.Dto2DomainMapper

class ExternalVideoResMapper: Dto2DomainMapper<ExternalServiceDto, ExternalVideoRes> {
    override fun toEntity(dtoObj: ExternalServiceDto): ExternalVideoRes {
        return ExternalVideoRes(
            serviceName = dtoObj.name,
            id = dtoObj.id
        )
    }

}