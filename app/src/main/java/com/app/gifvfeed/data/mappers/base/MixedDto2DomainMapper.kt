package com.app.gifvfeed.data.mappers.base

interface MixedDto2DomainMapper<DTO, DomainModel> :
    Dto2DomainMapper<DTO, DomainModel>,
    ListedDto2DomainMapper<DTO, DomainModel>