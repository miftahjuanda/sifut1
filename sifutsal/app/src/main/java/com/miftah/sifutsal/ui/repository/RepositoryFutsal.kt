package com.miftah.sifutsal.ui.repository

import com.miftah.sifutsal.Response.ResponseFutsal
import com.miftah.sifutsal.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryFutsal {

    fun getData(responseHandle: (ResponseFutsal) -> Unit, errorHandler: (Throwable) -> Unit){
        ConfigNetwork.service().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandle(it)
            },{
                errorHandler(it)
            })
    }
}