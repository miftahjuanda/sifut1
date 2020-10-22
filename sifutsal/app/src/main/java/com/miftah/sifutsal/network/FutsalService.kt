package com.miftah.sifutsal.network

import com.miftah.sifutsal.Response.ResponseFutsal
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface FutsalService {

    @GET("data")
    fun getData(): Flowable<ResponseFutsal>


}