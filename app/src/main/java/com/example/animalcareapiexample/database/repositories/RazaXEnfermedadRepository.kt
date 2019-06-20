package com.example.animalcareapiexample.database.repositories

import androidx.annotation.WorkerThread
import com.example.animalcareapiexample.database.daos.RazaXEnfermedadDao
import com.example.animalcareapiexample.database.entities.RazaXEnfermedad

class RazaXEnfermedadRepository(private val razaXEnfermedadDao: RazaXEnfermedadDao) {

    @WorkerThread
    suspend fun insertRazaXEnfermedad(razaXEnfermedad: RazaXEnfermedad):Long = razaXEnfermedadDao.insertRazaXEnf(razaXEnfermedad)

    fun getJoinTable() = razaXEnfermedadDao.getRazaXEnfJoin()
}