package com.example.animalcareapiexample.database.repositories

import android.util.Log
import com.example.animalcareapiexample.database.retrofit.AnimalService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SpecieRepository {

/*    fun retreiveSpecies() = GlobalScope.launch (Dispatchers.IO){

        val resp = AnimalService.getAnimalService().retreiveSpecies().await()
        if(resp.isSuccessful){
            with(resp){
                this.body()?.forEach {specie->
//                    Log.d("CUSTOM",specie.nombreEspecie)
                    specie.raza.forEach{raza->

                    }
                }
            }
        }
        else Log.e("CUSTOM","error")
    }*/
}