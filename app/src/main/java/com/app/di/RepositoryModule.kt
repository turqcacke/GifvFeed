package com.app.di

import com.app.gifvfeed.data.datasource.TimeLineDataSource
import com.app.gifvfeed.data.repository.TimeLineRepositoryImpl
import com.app.gifvfeed.domain.repository.TimeLineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTimeLineRepository(
        datasource: TimeLineDataSource
    ):TimeLineRepository{
        return TimeLineRepositoryImpl(datasource)
    }
}