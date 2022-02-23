package com.app.gifvfeed.domain.utils

interface MixedDto2DomainMapper<DTO, DomainModel> : Dto2DomainMapper<DTO, DomainModel>,
    ListedDto2DomainMapper<DTO, DomainModel>