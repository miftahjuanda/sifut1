package com.miftah.sifutsal.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ConfigNetwork {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://sifutsal.miftahjuan.mintoscreative.com/api/") //data
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun service(): FutsalService = getRetrofit().create(FutsalService::class.java)
}