package com.example.animalcareapiexample.database.repositories

import androidx.annotation.WorkerThread
import com.example.animalcareapiexample.database.daos.EnfermedadDao
import com.example.animalcareapiexample.database.entities.Enfermedad

class EnfermedadRepository (private val enfermedadDao: EnfermedadDao){
    @WorkerThread
    suspend fun insertEnfermedad(enfermedad: Enfermedad):Long = enfermedadDao.insert(enfermedad)
}