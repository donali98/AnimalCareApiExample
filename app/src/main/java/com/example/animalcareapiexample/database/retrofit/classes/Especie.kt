package com.example.animalcareapiexample.database.retrofit.classes

import com.squareup.moshi.Json

class Especie(
    @Json(name = "_id")
    val _id:String,
    @Json(name = "nombreEspecie")
    val nombreEspecie:String,
    @Json(name = "raza")
    val raza:List<Raza>
)