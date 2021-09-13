package studiosol.acerteonumero.network

import retrofit2.Call
import retrofit2.http.GET
import studiosol.acerteonumero.model.RandomNumber

interface ApiService {
    @GET("/rand?min=1&max=300")
    fun getRandomNumber() : Call<RandomNumber>
}