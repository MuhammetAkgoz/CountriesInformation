package com.example.kotlincountries.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomerSharedPreferences {

    companion object{
        private var sharedPreferences: SharedPreferences? = null
        private val PREFERENCES_TIME ="time"


        @Volatile private var instance : CustomerSharedPreferences ? = null

        private val lock = Any()

        //var mı yok mu
        operator fun invoke (context :Context) : CustomerSharedPreferences = instance ?: synchronized(lock){
            instance?: makeSharePreferences(context).also {
                instance=it
            }
        }


        private fun makeSharePreferences(context: Context): CustomerSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomerSharedPreferences()
        }
    }

    fun saveTime(time : Long){
        sharedPreferences?.edit(commit = true){
            putLong(PREFERENCES_TIME,time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME,0)

}