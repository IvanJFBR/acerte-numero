package com.ivstech.acerteonumero.network

import retrofit2.Call
import retrofit2.http.GET
import com.ivstech.acerteonumero.network.model.RandomNumber

interface ApiService {
    @GET("/rand?min=1&max=300")
    fun getRandomNumber(): Call<RandomNumber>
}