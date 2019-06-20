package com.example.animalcareapiexample.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animalcareapiexample.database.entities.Enfermedad

@Dao
interface EnfermedadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(enfermedad: Enfermedad):Long

    @Query("delete from enfermedad")
    suspend fun deleteAll()

}