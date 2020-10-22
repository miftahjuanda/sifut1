package com.miftah.sifutsal.ui.detail.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.sifutsal.Response.ResponseFutsal
import com.miftah.sifutsal.ui.repository.RepositoryFutsal

class DetailViewModel: ViewModel() {

    val repository = RepositoryFutsal()

    var responseData = MutableLiveData<ResponseFutsal?>()
    var isError = MutableLiveData<Throwable>()

    fun getData() {
        repository.getData({
            responseData.value = it
        },{
            isError.value = it
        })
    }

}