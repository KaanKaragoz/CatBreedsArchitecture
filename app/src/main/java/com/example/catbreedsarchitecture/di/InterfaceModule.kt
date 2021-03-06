package com.example.catbreedsarchitecture.di

import com.example.catbreedsarchitecture.data.source.local.FavoriteBreedsRepository
import com.example.catbreedsarchitecture.data.source.local.FavoriteBreedsRepositoryImpl
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModules {

    @Binds
    fun provideMainRepositoryImpl(repository: BreedsRepositoryImpl): BreedsRepository

    @Binds
    fun provideLocalRepositoryImpl(localRepository: FavoriteBreedsRepositoryImpl) : FavoriteBreedsRepository

}