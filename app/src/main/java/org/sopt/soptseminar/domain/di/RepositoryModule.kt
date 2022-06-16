package org.sopt.soptseminar.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.sopt.soptseminar.data.repositories.DefaultGithubProfileRepository
import org.sopt.soptseminar.data.repositories.DefaultUserAuthRepository
import org.sopt.soptseminar.domain.repositories.GithubProfileRepository
import org.sopt.soptseminar.domain.repositories.UserAuthRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGithubProfileRepository(
        defaultProfileRepository: DefaultGithubProfileRepository
    ): GithubProfileRepository

    @Binds
    abstract fun bindUserAuthRepository(
        defaultUserRepository: DefaultUserAuthRepository
    ): UserAuthRepository
}