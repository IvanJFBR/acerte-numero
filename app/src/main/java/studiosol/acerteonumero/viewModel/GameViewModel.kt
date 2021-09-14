package studiosol.acerteonumero.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import studiosol.acerteonumero.network.model.RandomNumber
import studiosol.acerteonumero.repository.RandomNumberRepository
import studiosol.acerteonumero.type.GameStatus

class GameViewModel(val repository: RandomNumberRepository) : ViewModel() {

    class Factory(private val repository: RandomNumberRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GameViewModel(repository) as T
        }
    }

    val randomNumber: MutableLiveData<RandomNumber> = MutableLiveData()
    var gameStatus: MutableLiveData<GameStatus> = MutableLiveData()

    var currentValue: MutableLiveData<Int> = MutableLiveData()

    /**
     * Valores a serem atribuídos à largura ou altura de acordo com a orientação do segmento.
     */
    var fontSize1: MutableLiveData<Int> = MutableLiveData()
    var fontSize2: MutableLiveData<Int> = MutableLiveData()

    var sliderValue: MutableLiveData<Int> = MutableLiveData()

    fun getRandomNumber() {
        viewModelScope.launch {
            repository.getRandomNumber().enqueue(object : Callback<RandomNumber> {
                override fun onResponse(
                    call: Call<RandomNumber>,
                    response: Response<RandomNumber>
                ) {
                    if (response.isSuccessful) {
                        randomNumber.postValue(response.body())
                    } else {
                        currentValue.postValue(response.raw().code)
                        gameStatus.postValue(GameStatus.Error)
                    }
                }

                override fun onFailure(call: Call<RandomNumber>, t: Throwable) {
                    gameStatus.postValue(GameStatus.Error)
                }
            })
        }
    }

    fun playGame(number: Int) {
        randomNumber.value?.let {
            gameStatus.value = when {
                number > it.number -> GameStatus.Lower
                number < it.number -> GameStatus.Higher
                else -> GameStatus.RightAnswer
            }

            currentValue.postValue(number)
        }
    }
}