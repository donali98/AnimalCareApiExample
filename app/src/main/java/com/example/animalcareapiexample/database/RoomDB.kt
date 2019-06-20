package com.example.animalcareapiexample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.animalcareapiexample.database.daos.EnfermedadDao
import com.example.animalcareapiexample.database.daos.RazaDao
import com.example.animalcareapiexample.database.daos.RazaXEnfermedadDao
import com.example.animalcareapiexample.database.entities.Enfermedad
import com.example.animalcareapiexample.database.entities.Raza
import com.example.animalcareapiexample.database.entities.RazaXEnfermedad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Enfermedad::class, Raza::class, RazaXEnfermedad::class], version = 3, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun razaDao(): RazaDao
    abstract fun enfermedadDao(): EnfermedadDao
    abstract fun razaXEnfermedadDao(): RazaXEnfermedadDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context,scope: CoroutineScope): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context, RoomDB::class.java, "animals")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class RoomDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDB(database.razaDao(), database.enfermedadDao(), database.razaXEnfermedadDao())
                }
            }
        }

        suspend fun populateDB(
            razaDao: RazaDao,
            enfermedadDao: EnfermedadDao,
            razaXEnfermedadDao: RazaXEnfermedadDao
        ) {
            razaXEnfermedadDao.deleteAll()
            razaDao.deleteAll()
            enfermedadDao.deleteAll()
        }
    }
}