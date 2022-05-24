package com.example.catbreeds.model

import androidx.room.TypeConverter
import com.example.catbreedsarchitecture.data.Items

class TypeConverter {

    @TypeConverter
    fun fromItems(bmp: Items): String {
        return bmp.url

    }

    @TypeConverter
    fun fromString(a: String) : Items {
        return Items(a)
    }
}