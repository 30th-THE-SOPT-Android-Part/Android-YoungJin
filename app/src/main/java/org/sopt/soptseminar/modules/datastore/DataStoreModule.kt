package org.sopt.soptseminar.modules.datastore

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {
    @Singleton
    @Binds
    abstract fun bindUserPreferenceManager(
        userDataManager: UserPreferenceManager
    ): UserPreferenceRepository
}