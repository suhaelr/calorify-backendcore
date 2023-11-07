package com.revaldi.calorify.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://calorify-dot-calorify-388602.et.r.appspot.com/"

    private const val BASE_URL_NEWS = "https://saurav.tech/"

    private const val BASE_MODEL = "https://machine-learning-model-zgch76kqxq-et.a.run.app/"
    val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val retrofitNews: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitModel: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_MODEL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val api: ApiEndpoints by lazy {
        retrofit.create(ApiEndpoints::class.java)
    }
    val apiNews: ApiEndpoints by lazy {
        retrofitNews.create(ApiEndpoints::class.java)
    }
    val apiModel: ApiEndpoints by lazy {
        retrofitModel.create(ApiEndpoints::class.java)
    }
}