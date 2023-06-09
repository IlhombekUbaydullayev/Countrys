package com.uzcoder.countries.network.service

import com.uzcoder.countries.model.WelcomeElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CountryService {
    @Headers(
        "Content-type:application/json"
    )
    @GET("v3.1/all")
    fun getAll(): Call<ArrayList<WelcomeElement>>

    @GET("v3.1/name/{country}")
    fun getByName(@Path("country") country : String): Call<ArrayList<WelcomeElement>>
}