package com.app.di

import com.app.gifvfeed.data.network.BaseUrls
import com.app.gifvfeed.data.network.entity.EntryBlockBase
import com.app.gifvfeed.data.network.entity.TimeLineItemDto
import com.app.gifvfeed.data.network.serialization.EntryBlockDeserializer
import com.app.gifvfeed.data.network.serialization.TimeLineItemDtoDeserializer
import com.app.gifvfeed.data.network.retrofit.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(TimeLineItemDto::class.java, TimeLineItemDtoDeserializer())
            .registerTypeAdapter(
                EntryBlockBase::class.java, EntryBlockDeserializer()
            ).create()
    }

    @Provides
    @Singleton
    fun provideApiService(
        gson: Gson,
    ): ApiService{

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().cache(null).build()

        return Retrofit.Builder()
            .baseUrl(BaseUrls.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

}