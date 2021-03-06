package com.example.catbreedsarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.catbreedsarchitecture.data.source.local.FavoriteBreedsDao
import com.example.catbreedsarchitecture.data.source.local.LikedCatsDatabase
import com.example.catbreedsarchitecture.api.ApiService
import com.example.catbreedsarchitecture.data.source.local.FavoriteBreedsLocalDataSource
import com.example.catbreedsarchitecture.data.source.local.FavoriteBreedsLocalDataSourceImpl
import com.example.catbreedsarchitecture.data.source.local.FavoriteBreedsRepository
import com.example.catbreedsarchitecture.data.source.remote.BreedsRemoteDataSourceImpl
import com.example.catbreedsarchitecture.data.source.remote.BreedsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*RETROFIT DI*/
    val BASE_URL = "https://api.thecatapi.com/v1/"
    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)

    }
    @Singleton
    @Provides
    fun getDataSource(apiService : ApiService, favoriteBreedsRepository: FavoriteBreedsRepository) : BreedsRemoteDataSource {
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

    /*ROOM DI*/
    @Singleton
    @Provides
    fun getLocalDataSource(breedsDao : FavoriteBreedsDao) : FavoriteBreedsLocalDataSource {
        return FavoriteBreedsLocalDataSourceImpl(breedsDao)
    }

    @Provides
    fun provideTaskDao(db: LikedCatsDatabase) = db.catsDao()

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        LikedCatsDatabase::class.java,
        "breeds_database"
    ).build()






}