package com.example.newsapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


const val BASE_URL="https://newsapi.org/"
const val API_Key = "312a04eea8f84405acf321c6d1131270"
interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_Key")
    fun getHeadLines(@Query("country") country:String,@Query("page")page:Int):Call<News>
}

object NewsService{
    val NewsInstace:NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        NewsInstace =retrofit.create(NewsInterface::class.java)
    }
}
