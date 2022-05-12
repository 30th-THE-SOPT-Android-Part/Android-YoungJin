package org.sopt.soptseminar.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.soptseminar.data.api.GithubService
import org.sopt.soptseminar.data.api.GithubServiceCreator
import org.sopt.soptseminar.data.api.SoptService
import org.sopt.soptseminar.data.api.SoptServiceCreator
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceBinder {
    @Singleton
    @Provides
    fun bindSoptService(): SoptService {
        return SoptServiceCreator.soptService
    }

    @Singleton
    @Provides
    fun bindGithubService(): GithubService {
        return GithubServiceCreator.githubService
    }
}