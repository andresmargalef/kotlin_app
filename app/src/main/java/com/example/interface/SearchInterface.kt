package com.example.`interface`

import com.example.pojo.Results
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchInterface {
    @GET("sites/MLU/search?")
    fun searchResult(@Query("q") search: String):
            Observable<Results>


    companion object {
        fun create(): SearchInterface {
            // addCallAdapterFactory -> para serializar y deserializar objetos
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.mercadolibre.com/")
                .build()
            // ::class.java entiendo que es la forma de levantar las clases java desde kotlin
            return retrofit.create(SearchInterface::class.java)
        }
    }
}