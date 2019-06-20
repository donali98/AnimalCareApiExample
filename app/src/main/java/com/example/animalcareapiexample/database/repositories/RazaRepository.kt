package com.example.animalcareapiexample.database.repositories

import androidx.annotation.WorkerThread
import com.example.animalcareapiexample.database.daos.RazaDao
import com.example.animalcareapiexample.database.entities.Raza

class RazaRepository (private val razaDao: RazaDao){
    @WorkerThread
    suspend fun insertRaza(raza:Raza):Long = razaDao.insert(raza)
}