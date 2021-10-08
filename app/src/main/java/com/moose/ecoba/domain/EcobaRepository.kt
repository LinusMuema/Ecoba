package com.moose.ecoba.domain

import com.moose.ecoba.domain.models.Credentials
import com.moose.ecoba.domain.models.User
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow

interface EcobaRepository {
    val user: Flow<User>

    suspend fun addUser(user: User)

    suspend fun login(credentials: Credentials)
}

// bind the implementation to the interface
@Module
@InstallIn(ViewModelComponent::class)
abstract class EcobaRepositoryBinding {

    @Binds
    abstract fun provideRepository(impl: EcobaRepositoryImpl): EcobaRepository
}