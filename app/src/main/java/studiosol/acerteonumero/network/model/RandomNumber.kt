package studiosol.acerteonumero.network.model

import com.google.gson.annotations.SerializedName

data class RandomNumber(
    @SerializedName("value")
    val number: Int
)
