package com.example.animalcareapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.animalcareapiexample.database.repositories.SpecieRepository
import com.example.animalcareapiexample.viewmodels.SpecieViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val specieViewModel = ViewModelProviders.of(this).get(SpecieViewModel::class.java)
        specieViewModel.crossTableLiveData.observe(this, Observer {lista->

            if(lista.isNotEmpty()){
                lista.forEach {
                    Log.d("CUSTOM",it.nombreRaza)
                }
            }
        })
        specieViewModel.insertData()

    }

}
