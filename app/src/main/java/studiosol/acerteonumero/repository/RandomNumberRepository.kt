package studiosol.acerteonumero.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import studiosol.acerteonumero.model.RandomNumber
import studiosol.acerteonumero.network.services
import studiosol.acerteonumero.util.Constants.Companion.FINAL_RANGE
import studiosol.acerteonumero.util.Constants.Companion.INITIAL_RANGE

class RandomNumberRepository {

    fun getRandomNumber() : Call<RandomNumber> =
        services().getRandomNumber()
}