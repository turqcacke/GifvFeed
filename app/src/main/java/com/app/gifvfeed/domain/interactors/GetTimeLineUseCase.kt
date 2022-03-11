package com.app.gifvfeed.domain.interactors

import com.app.gifvfeed.domain.entity.SetParams
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.app.gifvfeed.domain.repository.TimeLineRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class GetTimeLineUseCase @Inject constructor(
    private val timeLineRepository: TimeLineRepository
) {
    suspend operator fun invoke(
        lastId: Int? = null,
        lastSortingValue: Int? = null
    ): Result<Pair<List<TimeLineItem>, SetParams>> {

        return try {
            val data = timeLineRepository.getTimeline(
                lastSortingValue = lastSortingValue,
                lastId = lastId
            )

            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}