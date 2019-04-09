package com.example.`interface`

import com.example.pojo.ItemDescription
import com.example.pojo.Results
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("sites/MLU/search?")
    fun searchResult(@Query("q") search: String):
            Observable<Results>

    @GET("items/{itemId}/description")
    fun descriptionResult(@Path("itemId") itemId: String):
            Observable<ItemDescription>


    companion object {
        fun createSearchInterface(): ApiInterface {
            // addCallAdapterFactory -> para serializar y deserializar objetos
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.mercadolibre.com/")
                .build()
            // ::class.java entiendo que es la forma de levantar las clases java desde kotlin con reflection
            return retrofit.create(ApiInterface::class.java)
        }

        fun createItemInterface(): ApiInterface {
            val logginInterceptor = HttpLoggingInterceptor()
            logginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder().addInterceptor(logginInterceptor).build()

            // addCallAdapterFactory -> para serializar y deserializar objetos
            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.mercadolibre.com/")
                .build()
            // ::class.java entiendo que es la forma de levantar las clases java desde kotlin con reflection
            return retrofit.create(ApiInterface::class.java)
        }
    }
}