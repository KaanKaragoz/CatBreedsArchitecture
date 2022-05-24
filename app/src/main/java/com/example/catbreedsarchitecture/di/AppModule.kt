package com.example.catbreedsarchitecture.di

import com.example.catbreedsarchitecture.api.ApiService
import com.example.catbreedsarchitecture.data.source.remote.BreedsRemoteDataSourceImpl
import com.example.catbreedsarchitecture.data.source.remote.BreedsRemoteDataSourcee
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val BASE_URL = "https://api.thecatapi.com/v1/"

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)

    }
    @Singleton
    @Provides
    fun getDataSource(apiService : ApiService) : BreedsRemoteDataSourcee {
        return BreedsRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance() : Retrofit{

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

    }




}