package com.example.animalcareapiexample.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "raza")
data class Raza(

    @ColumnInfo(name = "nombre")
    val nombreRaza:String
){

    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
    @ColumnInfo(name = "descripcion")
    var descripcion:String = ""
}
