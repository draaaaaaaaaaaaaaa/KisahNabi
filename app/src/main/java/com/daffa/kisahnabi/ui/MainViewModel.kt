package com.daffa.kisahnabi.ui

import android.database.DatabaseErrorHandler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.daffa.kisahnabi.data.ApiClient
import com.daffa.kisahnabi.data.KisahResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel :ViewModel() {
    //transaksi data dari kisah response ibarat kabel casan kalu gak kesam bung hp nya gak ke cast

    val KisahResponse = MutableLiveData<List<KisahResponse>>()
    val isLoading = MutableLiveData<Boolean>()
    val  isError = MutableLiveData<Throwable>()

    fun getKisahNabi(responseHandle: (List<KisahResponse>)-> Unit ,errorHandler: (Throwable)-> Unit){
        ApiClient.getApiService().getKisahNabi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandle(it)

            }, {
                errorHandler(it)
            })
    }
    fun getData(){
        isLoading.value = true
        getKisahNabi({
            isLoading.value = true
            KisahResponse.value = it
        },{
            isLoading.postValue(false)
            isError.value = it

        })
    }
}