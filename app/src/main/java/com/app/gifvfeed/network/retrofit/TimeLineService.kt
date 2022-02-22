package com.app.gifvfeed.network.retrofit

import com.app.gifvfeed.network.responses.TimeLineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeLineService {
    @GET("timeline")
    suspend fun timeline(
        @Query("allSite") allSite: Boolean = false,
        @Query("sorting") sorting:String ="date",
        @Query("subsitesIds") subsitesIds: String = "237832"
    ): TimeLineResponse
}