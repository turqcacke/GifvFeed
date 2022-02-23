package com.app.gifvfeed.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.gifvfeed.R
import com.app.gifvfeed.domain.usecase.GetTimeLineUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GifvFeedActivity : AppCompatActivity() {

    @Inject
    lateinit var useCase: GetTimeLineUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gifv_feed)

//        val logInterceptor = HttpLoggingInterceptor()
//        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()
//
//        val gson = GsonBuilder()
//            .registerTypeAdapter(TimeLineItemDto::class.java, TimeLineItemDtoDeserializer())
//            .registerTypeAdapter(EntryBlockBase::class.java, EntryBlockDeserializer()
//
////            .registerTypeAdapterFactory(
////                RuntimeTypeAdapterFactory.of(
////                    EntryBlockBase::class.java, "type"
////                )
////                    .registerSubtype(
////                        MediaEntryBlock::class.java,
////                        EntryBlockBase.Type.MEDIA.toString()
////                    )
////                    .registerSubtype(
////                        VideoEntryBlock::class.java,
////                        EntryBlockBase.Type.VIDEO.toString()
////                    )
////                    .registerSubtype(
////                        TextEntryBlock::class.java,
////                        EntryBlockBase.Type.TEXT.toString()
////                    )
//            )
//            .create()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.tjournal.ru/v2.1/")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService::class.java)
//
//        CoroutineScope(IO).launch {
//            try {
//                val timelineResp = retrofit.timeline()
//                Log.i("TimeLineResp", timelineResp.toString())
//            }catch (e: Exception){
//                Log.e("NetworkError", e.stackTraceToString())
//            }
//        }
        CoroutineScope(IO).launch{
            val result = useCase()
            when{
                result.isSuccess -> {
                    Log.d("UseCase", result.getOrNull().toString())
                }
                result.isFailure ->{
                    Log.e("UseCase", "Error", result.exceptionOrNull())
                }
            }
        }
    }
}