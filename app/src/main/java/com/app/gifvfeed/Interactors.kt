package com.app.gifvfeed
import com.app.gifvfeed.domain.interactors.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class Interactors @Inject constructor(
    val getTimeLineUseCase: GetTimeLineUseCase
)
