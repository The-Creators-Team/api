package com.thecreator.codechallengeapicall.di

import com.thecreator.codechallengeapicall.api.ApiService
import com.thecreator.codechallengeapicall.api.VideoRepo
import com.thecreator.codechallengeapicall.api.VideoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiService.BASE_URL)
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesVideoRepo(apiService:ApiService): VideoRepo = VideoRepoImpl(apiService)


}