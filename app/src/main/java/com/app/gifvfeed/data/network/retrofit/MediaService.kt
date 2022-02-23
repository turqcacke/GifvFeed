package com.app.gifvfeed.data.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface MediaService {
    @GET("{mediaKey}/-/scale_crop/{cropResolution}")
    fun getImage(
        @Path("mediaKey")
        mediaKey: String,
        @Path("cropResolution")
        cropResolution: String? = null
    )
}