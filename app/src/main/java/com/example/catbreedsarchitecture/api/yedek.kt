package com.example.catbreedsarchitecture.api

import com.example.catbreedsarchitecture.data.source.remote.BreedsRemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class yedek {
    private var BASE_URL = "https://api.thecatapi.com/v1/"

    fun create() : BreedsRemoteDataSource {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(BreedsRemoteDataSource::class.java)
    }
}