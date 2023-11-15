package com.example.demopetapp.di

import com.example.demopetapp.data.manager.AccessTokenManagerImpl
import com.example.demopetapp.data.repository.AnimalDetailsRepositoryImpl
import com.example.demopetapp.data.repository.AnimalListRepositoryImpl
import com.example.demopetapp.domain.manager.AccessTokenManager
import com.example.demopetapp.domain.repository.AnimalDetailsRepository
import com.example.demopetapp.domain.repository.AnimalListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAnimalListRepository(
        animalListRepositoryImpl: AnimalListRepositoryImpl
    ): AnimalListRepository

    @Binds
    abstract fun bindAnimalDetailsRepository(
        animalDetailsRepositoryImpl: AnimalDetailsRepositoryImpl
    ): AnimalDetailsRepository

    @Binds
    abstract fun bindAccessTokenManager(
        accessTokenManagerImpl: AccessTokenManagerImpl
    ) : AccessTokenManager
}
