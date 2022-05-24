package com.example.catbreedsarchitecture.api

import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepository
import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepositoryImpl
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRepositoryImpl
import com.example.catbreedsarchitecture.ui.home.BreedsClickListener
import com.example.catbreedsarchitecture.ui.home.HomeViewModel
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
    fun provideLocalRepositoryImpl(localRepository: BreedsLocalRepositoryImpl) : BreedsLocalRepository

}