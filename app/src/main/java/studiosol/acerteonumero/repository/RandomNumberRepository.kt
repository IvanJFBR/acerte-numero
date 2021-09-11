package studiosol.acerteonumero.repository

import studiosol.acerteonumero.model.RandomNumber
import studiosol.acerteonumero.network.services
import studiosol.acerteonumero.util.Constants.Companion.FINAL_RANGE
import studiosol.acerteonumero.util.Constants.Companion.INITIAL_RANGE

class RandomNumberRepository {

    suspend fun getRandomNumber() : RandomNumber =
        services().getRandomNumber()
}