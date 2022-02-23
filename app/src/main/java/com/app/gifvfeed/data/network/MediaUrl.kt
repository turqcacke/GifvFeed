package com.app.gifvfeed.data.network

import android.net.Uri

object MediaUrl {

    private const val scheme = "https"
    private val BASE_URL = BaseUrls.MEDIA_AUTHORITY


    enum class CorpRes(val resolution: String){
        R22x22("22x22"),
    }

    fun getImageCorpUrl(mediaKey: String, resolution: CorpRes=CorpRes.R22x22): String{
        val builder = Uri.Builder()
        return builder.scheme(scheme)
            .authority(BASE_URL)
            .appendPath(mediaKey)
            .appendPath("-")
            .appendPath("scale_crop")
            .appendPath(resolution.resolution)
            .build()
            .toString()
    }
}