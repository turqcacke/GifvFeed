package com.app.gifvfeed.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.gifvfeed.R
import com.app.gifvfeed.network.entity.*
import com.app.gifvfeed.network.retrofit.ApiService
import com.app.gifvfeed.network.serialization.EntryBlockDeserializer
import com.app.gifvfeed.network.serialization.TimeLineItemDtoDeserializer
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GifvFeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gifv_feed)

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

        val gson = GsonBuilder()
            .registerTypeAdapter(TimeLineItemDto::class.java, TimeLineItemDtoDeserializer())
            .registerTypeAdapter(EntryBlockBase::class.java, EntryBlockDeserializer()
//            .registerTypeAdapterFactory(
//                RuntimeTypeAdapterFactory.of(
//                    EntryBlockBase::class.java, "type"
//                )
//                    .registerSubtype(
//                        MediaEntryBlock::class.java,
//                        EntryBlockBase.Type.MEDIA.toString()
//                    )
//                    .registerSubtype(
//                        VideoEntryBlock::class.java,
//                        EntryBlockBase.Type.VIDEO.toString()
//                    )
//                    .registerSubtype(
//                        TextEntryBlock::class.java,
//                        EntryBlockBase.Type.TEXT.toString()
//                    )
            )
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.tjournal.ru/v2.1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)

        CoroutineScope(IO).launch {
            val timelineResp = retrofit.timeline()
            Log.i("TimeLineResp", timelineResp.toString())
        }
    }
}