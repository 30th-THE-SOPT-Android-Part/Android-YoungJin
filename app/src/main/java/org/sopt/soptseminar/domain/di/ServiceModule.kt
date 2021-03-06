package org.sopt.soptseminar.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.soptseminar.data.services.GithubService
import org.sopt.soptseminar.data.services.SoptService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    private const val SIGN_BASE_URL = "http://13.124.62.236/"
    private const val GITHUB_BASE_URL = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideSoptService(): SoptService =
        Retrofit.Builder().baseUrl(SIGN_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(SoptService::class.java)

    @Singleton
    @Provides
    fun provideGithubService(): GithubService =
        Retrofit.Builder().baseUrl(GITHUB_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(GithubService::class.java)
}