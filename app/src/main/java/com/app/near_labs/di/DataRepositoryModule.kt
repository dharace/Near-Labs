package com.app.near_labs.di

import com.app.near_labs.data.api.ApiService
import com.app.near_labs.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }
}