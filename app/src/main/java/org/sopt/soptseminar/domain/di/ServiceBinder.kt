package org.sopt.soptseminar.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.soptseminar.data.api.SoptService
import org.sopt.soptseminar.data.api.SoptServiceCreator
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceBinder {
    @Singleton
    @Provides
    fun bindFriendDatabase(): SoptService {
        return SoptServiceCreator.soptService
    }
}