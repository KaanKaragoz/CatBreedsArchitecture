package com.example.catbreedsarchitecture.data.source.local


import androidx.room.*
import com.example.catbreeds.model.TypeConverter
import com.example.catbreedsarchitecture.data.Breed

@Dao
@TypeConverters(TypeConverter::class)
interface BreedsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCat(cat: Breed)

    @Query("SELECT * FROM liked_cats_table ORDER BY catId")
    suspend fun readAllData(): List<Breed>

    @Query("DELETE FROM liked_cats_table WHERE name =:name ")
    suspend fun deleteCat(name : String)
}