package studiosol.acerteonumero.repository

import retrofit2.Call
import studiosol.acerteonumero.network.model.RandomNumber
import studiosol.acerteonumero.network.services

class RandomNumberRepository {
    fun getRandomNumber(): Call<RandomNumber> =
        services().getRandomNumber()
}