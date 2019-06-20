package com.example.animalcareapiexample.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animalcareapiexample.database.entities.Raza

@Dao
interface RazaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(raza:Raza):Long

    @Query("delete from raza")
    suspend fun deleteAll()
}