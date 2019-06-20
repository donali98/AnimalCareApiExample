package com.example.animalcareapiexample.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animalcareapiexample.database.entities.Raza
import com.example.animalcareapiexample.database.entities.RazaXEnfermedad


@Dao
interface RazaXEnfermedadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRazaXEnf(razaXEnfermedad: RazaXEnfermedad):Long

    @Query("""
        select * from raza inner join raza_x_enfermedad 
        on raza.id = raza_x_enfermedad.raza_id
    """)
    fun getRazaXEnfJoin():LiveData<List<Raza>>

    @Query("delete from raza_x_enfermedad")
    suspend fun deleteAll()

}