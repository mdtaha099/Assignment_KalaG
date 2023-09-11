package com.example.myapplication.retrofit

import com.example.myapplication.Base_Url
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    val BASE_URL = "https://jsonplaceholder.typicode.com/"

//    fun getClient() : Retrofit.Builder()
//            .baseUrl(Base_Url)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(PostsAPI::class.java)

//    fun getClient() : Retrofit{
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create() ))
//            .build()
//    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getClient(): Retrofit {
        return retrofit
    }
}