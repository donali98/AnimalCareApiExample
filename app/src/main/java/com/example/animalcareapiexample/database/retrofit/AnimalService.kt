package com.example.animalcareapiexample.database.retrofit

import com.example.animalcareapiexample.database.retrofit.classes.Especie
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://animal-care.herokuapp.com"

interface AnimalService {
    @GET("especie")
    fun retreiveSpecies():Deferred<Response<List<Especie>>>
    companion object{
        fun getAnimalService():AnimalService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(AnimalService::class.java)
    }
}