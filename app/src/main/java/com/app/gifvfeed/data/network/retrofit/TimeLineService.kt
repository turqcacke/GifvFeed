package com.app.gifvfeed.data.network.retrofit

import com.app.gifvfeed.data.network.responses.TimeLineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeLineService {
    @GET("timeline")
    suspend fun timeline(
        @Query("allSite") allSite: Boolean = false,
        @Query("sorting") sorting:String ="date",
        @Query("subsitesIds") subsitesIds: String = "458157",
        @Query("lastId") lastId: Int? = null,
        @Query("lastSortingValue") lastSortingValue: Int? = null
    ): TimeLineResponse
}