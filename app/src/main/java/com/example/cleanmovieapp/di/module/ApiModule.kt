package com.example.cleanmovieapp.di.module

import com.example.cleanmovieapp.data.remote.MovieApi
import com.example.cleanmovieapp.util.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor { chain ->

                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${Constants.ACCESS_TOKEN}")
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    single<MovieApi> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(MovieApi::class.java)
    }

}