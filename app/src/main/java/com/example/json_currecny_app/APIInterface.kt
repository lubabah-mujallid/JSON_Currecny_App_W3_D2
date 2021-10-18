package com.example.json_currecny_app

import com.example.json_currecny_app.CurrencyDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("eur.json")
    fun getCurrency(): Call<CurrencyDetails>?
}