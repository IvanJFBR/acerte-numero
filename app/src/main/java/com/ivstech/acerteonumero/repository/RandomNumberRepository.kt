package com.ivstech.acerteonumero.repository

import retrofit2.Call
import com.ivstech.acerteonumero.network.model.RandomNumber
import com.ivstech.acerteonumero.network.services

class RandomNumberRepository {
    fun getRandomNumber(): Call<RandomNumber> =
        services().getRandomNumber()
}