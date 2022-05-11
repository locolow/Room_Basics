package com.example.roombasicscodelab.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roombasicscodelab.db.CityDB
import com.example.roombasicscodelab.db.CityRepository
import com.example.roombasicscodelab.entities.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: CityRepository

    val allCities: LiveData<List<City>>

    init {
        val citiesDao = CityDB.getDatabase(application,viewModelScope).cityDao()
        repository = CityRepository(citiesDao)
        allCities = repository.allCities
    }

    fun insert(city: City) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(city)
    }
}