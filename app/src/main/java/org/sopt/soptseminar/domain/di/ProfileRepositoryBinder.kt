package org.sopt.soptseminar.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.sopt.soptseminar.data.repositories.DefaultGithubProfileRepository
import org.sopt.soptseminar.domain.GithubProfileRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileRepositoryBinder {
    @Binds
    abstract fun bindGithubProfileRepository(
        defaultProfileRepository: DefaultGithubProfileRepository
    ): GithubProfileRepository
}