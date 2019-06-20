package com.example.animalcareapiexample.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "raza_x_enfermedad",
    primaryKeys =["raza_id","enfermedad_id"],
    foreignKeys = [
        ForeignKey(
            entity = Raza::class,
            parentColumns = ["id"],
            childColumns = ["raza_id"]
        ),
        ForeignKey(
            entity = Enfermedad::class,
            parentColumns = ["id"],
            childColumns = ["enfermedad_id"]
        )
    ]
)
data class RazaXEnfermedad(
    val raza_id:Long,
    val enfermedad_id:Long
)