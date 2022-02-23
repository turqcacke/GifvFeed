package com.app.gifvfeed.domain.usecase

import com.app.gifvfeed.domain.entity.TimeLineItem
import com.app.gifvfeed.domain.repository.TimeLineRepository
import javax.inject.Inject

class GetTimeLineUseCase @Inject constructor(
    private val timeLineRepository: TimeLineRepository
) {
    suspend operator fun invoke(): Result<List<TimeLineItem>> {
        return try {
            val data = timeLineRepository.getTimeline()
            Result.success(data)
        } catch (e: Error) {
            Result.failure(e)
        }
    }
}