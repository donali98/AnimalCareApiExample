package com.example.animalcareapiexample.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.animalcareapiexample.database.RoomDB
import com.example.animalcareapiexample.database.entities.Enfermedad
import com.example.animalcareapiexample.database.repositories.EnfermedadRepository
import com.example.animalcareapiexample.database.repositories.RazaRepository
import com.example.animalcareapiexample.database.retrofit.AnimalService
import com.example.animalcareapiexample.database.entities.Raza
import com.example.animalcareapiexample.database.entities.RazaXEnfermedad
import com.example.animalcareapiexample.database.repositories.RazaXEnfermedadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecieViewModel(private val app: Application) : AndroidViewModel(app) {
    private val enfermedadRepository: EnfermedadRepository
    private val razaRepository: RazaRepository
    private val razaXEnfermedadRepository: RazaXEnfermedadRepository
    var insertedId: MutableLiveData<Long> = MutableLiveData(0)


    init {
        val enfermedadDao = RoomDB.getInstance(app, viewModelScope).enfermedadDao()
        val razaDao = RoomDB.getInstance(app, viewModelScope).razaDao()
        val razaXEnf = RoomDB.getInstance(app, viewModelScope).razaXEnfermedadDao()

        enfermedadRepository = EnfermedadRepository(enfermedadDao)
        razaRepository = RazaRepository(razaDao)
        razaXEnfermedadRepository = RazaXEnfermedadRepository(razaXEnf)
    }

    var crossTableLiveData = Transformations.switchMap(insertedId) {
        getJoinTable()
    }


    fun insertData() = viewModelScope.launch(Dispatchers.IO) {
        val resp = AnimalService.getAnimalService().retreiveSpecies().await()
        if (resp.isSuccessful) {
            with(resp) {
                this.body()?.forEach { specie ->
                    //                    Log.d("CUSTOM",specie.nombreEspecie)
                    specie.raza.forEach { raza ->
                        var newRaza = Raza(raza.nombreRaza)
                        val newRaceId = razaRepository.insertRaza(newRaza)

                        raza.enfermedades.forEach { enfermedad ->
                            //                            Log.d("CUSTOM",enfermedad._id)
                            val newEnfermedad =
                                Enfermedad(enfermedad.nombreEnfermedad, enfermedad.descripccionEnfermedad)
                            val newEnfermedadId = enfermedadRepository.insertEnfermedad(newEnfermedad)
                            razaXEnfermedadRepository.insertRazaXEnfermedad(RazaXEnfermedad(newRaceId, newEnfermedadId))
//                            setDataToLiveData(newRaceId )
                            insertedId.postValue(newRaceId)
                        }
                    }

                }
            }
        } else Log.e("CUSTOM", "error")
    }

    fun getJoinTable() = razaXEnfermedadRepository.getJoinTable()

}