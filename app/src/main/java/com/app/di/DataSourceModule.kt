package com.app.di

import com.app.gifvfeed.data.datasource.TimeLineDataSource
import com.app.gifvfeed.data.datasource.TimeLineRetrofitSource
import com.app.gifvfeed.data.network.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideTimeLineDataSource(
        apiService: ApiService
    ): TimeLineDataSource = TimeLineRetrofitSource(apiService)
}