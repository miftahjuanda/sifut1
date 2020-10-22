package com.miftah.sifutsal.ui.beranda.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.sifutsal.Response.ResponseFutsal
import com.miftah.sifutsal.ui.repository.RepositoryFutsal

class MainViewModel: ViewModel() {

    val repository = RepositoryFutsal()

    var responseData = MutableLiveData<ResponseFutsal?>()
    var isError = MutableLiveData<Throwable>()

    fun getData() {
        repository.getData({
            /*if (responseData.value != null) {
                responseData.value = it
            }*/
            responseData.value = it
        },{
            isError.value = it
        })
    }
}