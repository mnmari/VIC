package com.mariana.moviedbpi.data.repository

import com.mariana.moviedbpi.data.model.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {

//    private val client = OkHttpClient.Builder().build()
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(Constants.BASE_URL.value)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(client)
//        .build()
//
//    fun<T> buildService(service: Class<T>): T{
//        return retrofit.create(service)
//    }

    fun getService(): TMDBService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Constants.PRIVATE_KEY.value)
                .addQueryParameter("language", Constants.LANGUAGE.value)
                .build()

            chain.proceed(original.newBuilder().url(url).build())
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL.value)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        return retrofit.create(TMDBService::class.java)
    }
}