package studiosol.acerteonumero.network

import retrofit2.http.GET

interface ApiService {
    @GET("/rand?min=1&max=300")
    suspend fun getRandomNumber()
}