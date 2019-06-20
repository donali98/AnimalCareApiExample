package com.example.animalcareapiexample.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "enfermedad")
class Enfermedad (
    @ColumnInfo(name = "nombre")
    val nombreEnfermedad:String,
    @ColumnInfo(name = "descripcion")
    val descripcionEnfermedad:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}