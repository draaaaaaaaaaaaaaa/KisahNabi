package com.daffa.kisahnabi.network

import com.daffa.kisahnabi.data.KisahResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("kisahnabi")
    fun  getKisahNabi() : Flowable<List<KisahResponse>>
}