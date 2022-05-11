package com.example.roombasicscodelab.db

import androidx.lifecycle.LiveData
import com.example.roombasicscodelab.dao.CityDao
import com.example.roombasicscodelab.entities.City

class CityRepository (private val cityDao: CityDao){
    val allCities: LiveData<List<City>> = cityDao.getAlphabetizedCities()

    suspend fun insert(city: City){
        cityDao.insert(city)
    }
}