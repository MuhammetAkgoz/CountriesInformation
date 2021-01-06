package com.example.kotlincountries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlincountries.model.Country


@Database(entities = arrayOf(Country::class),version = 1)
abstract class CountryDatabase : RoomDatabase(){

    abstract fun countryDao() : CountryDAO

    //Singeltion
    companion object{
        @Volatile private var instance :CountryDatabase? = null


        private val lock = Any()
        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {

                instance = it
            }
        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,CountryDatabase ::class.java, "countrydatabase"
        ).build()

    }

}