package org.sopt.soptseminar.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.sopt.soptseminar.data.repositories.DefaultUserAuthRepository
import org.sopt.soptseminar.domain.UserAuthRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserAuthRepositoryBinder {
    @Binds
    abstract fun bindUserAuthRepository(
        defaultUserRepository: DefaultUserAuthRepository
    ): UserAuthRepository
}