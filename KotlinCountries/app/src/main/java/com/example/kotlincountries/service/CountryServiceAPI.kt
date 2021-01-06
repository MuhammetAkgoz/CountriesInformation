package com.example.kotlincountries.service

import com.example.kotlincountries.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryServiceAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //Birincisi baseURL+>https://raw.githubusercontent.com/

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private  val api = Retrofit.Builder()
        .baseUrl(BASE_URL) //URL veriyoruz
        .addConverterFactory(GsonConverterFactory.create()) //Gson verileri kullanacağımızı söylüyotuz
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Rx java kullanacağımızı söylememiz gerekiyor
        .build() //build etmesini istiyoruz
        .create(CountryAPI::class.java) //olusturmasını istiyoruz


    fun getData() : Single<List<Country>>{
        return api.getCountries()
    }
}