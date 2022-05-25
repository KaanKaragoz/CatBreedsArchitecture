package com.example.catbreedsarchitecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.catbreedsarchitecture.util.TypeConverter
import com.example.catbreedsarchitecture.data.Breed



@Database(entities = [Breed::class], version =1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class LikedCatsDatabase :RoomDatabase() {
    abstract fun catsDao() : FavoriteBreedsDao
}