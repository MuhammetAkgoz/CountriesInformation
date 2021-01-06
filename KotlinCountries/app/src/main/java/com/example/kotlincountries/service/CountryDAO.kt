package com.example.kotlincountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlincountries.model.Country

@Dao
interface CountryDAO {

    //Data Acess Object

    @Insert
    suspend fun insertAll(vararg countries : Country): List<Long>

    //Insert -> Insert Into
    // suspend - > coroutine , pause & resume
    //vararg -> multiple country object
    //List<Long> - > primary keys


    @Query("Select * From country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT* FROM Country Where uuid= :id")
    suspend fun getCountry(id : Int) : Country

    @Query("DELETE From Country")
    suspend fun deleteAllCountries()


}