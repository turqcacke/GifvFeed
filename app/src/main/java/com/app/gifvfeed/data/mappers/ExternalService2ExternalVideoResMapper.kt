package com.app.gifvfeed.data.mappers
import com.app.gifvfeed.data.network.entity.ExternalServiceDto
import com.app.gifvfeed.domain.entity.ExternalVideoRes
import com.app.gifvfeed.data.mappers.base.Dto2DomainMapper

class ExternalService2ExternalVideoResMapper:
    Dto2DomainMapper<ExternalServiceDto, ExternalVideoRes> {
    override fun toEntity(dtoObj: ExternalServiceDto?): ExternalVideoRes? {
        dtoObj?.let {
            return ExternalVideoRes(
                serviceName = it.name,
                id = it.id
            )
        }
        return null
    }

}