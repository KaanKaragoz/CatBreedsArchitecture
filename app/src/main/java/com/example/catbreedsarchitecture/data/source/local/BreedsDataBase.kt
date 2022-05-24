package com.example.catbreeds.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.catbreeds.model.TypeConverter
import com.example.catbreedsarchitecture.data.Breed


@Database(entities = [Breed::class], version =1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class LikedCatsDatabase :RoomDatabase() {

    abstract fun catsDao() : BreedsDao

}