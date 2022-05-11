package com.example.roombasicscodelab.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roombasicscodelab.dao.CityDao
import com.example.roombasicscodelab.entities.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(City::class), version = 4, exportSchema = false)
public abstract class CityDB : RoomDatabase(){
    abstract fun cityDao(): CityDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let{ database ->
                scope.launch {
                    var cityDao = database.cityDao()

                    //Delete all content here
                    cityDao.deleteAll()

                    //add Sample words.
                    var city = City(1,"Viana do Castelo","Portugal")
                    cityDao.insert(city)
                    city = City(2,"Porto","Portugal")
                    cityDao.insert(city)
                }
            }
        }
    }
    companion object{
        @Volatile
        private var INSTANCE: CityDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CityDB{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityDB::class.java,
                    "cities_database",
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}