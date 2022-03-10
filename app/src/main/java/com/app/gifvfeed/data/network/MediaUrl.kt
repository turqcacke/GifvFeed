package com.app.gifvfeed.data.network

import android.net.Uri

object MediaUrl {

    private const val scheme = "https"
    private val BASE_URL = BaseUrls.MEDIA_AUTHORITY


    enum class CorpRes(val resolution: String) {
        R22x22("22x22"),
        NO_CROP("")
    }

    enum class VideoFormat(val format: String){
        MP4("mp4")
    }

    fun getImageCorpUrl(mediaKey: String, resolution: CorpRes = CorpRes.NO_CROP): String {
        val builder = Uri.Builder().scheme(scheme)
            .authority(BASE_URL)
            .appendPath(mediaKey)




        if (resolution.name == CorpRes.NO_CROP.name) {
            return builder.toString()
        }
        return builder
            .appendPath("-")
            .appendPath("scale_crop")
            .appendPath(resolution.resolution)
            .build()
            .toString()
    }

    fun getVideoUrl(mediaKey: String, format: VideoFormat = VideoFormat.MP4): String{
        val builder = Uri.Builder().scheme(scheme)
            .authority(BASE_URL)
            .appendPath(mediaKey)
            .appendPath("-")
            .appendPath("format")
            .appendPath(format.format)
        return builder.build().toString()
    }
}