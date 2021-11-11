package com.ivstech.acerteonumero.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.ivstech.acerteonumero.network.model.RandomNumber
import com.ivstech.acerteonumero.repository.RandomNumberRepository
import com.ivstech.acerteonumero.type.GameStatus

class GameViewModel(val repository: RandomNumberRepository) : ViewModel() {

    class Factory(private val repository: RandomNumberRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GameViewModel(repository) as T
        }
    }

    val randomNumber: MutableLiveData<Int> = MutableLiveData()
    var gameStatus: MutableLiveData<GameStatus> = MutableLiveData()

    var currentValue: MutableLiveData<Int> = MutableLiveData()

    /**
     * Valores a serem atribuídos à largura ou altura de acordo com a orientação do segmento.
     */
    var fontSize1: MutableLiveData<Int> = MutableLiveData()
    var fontSize2: MutableLiveData<Int> = MutableLiveData()

    var sliderValue: MutableLiveData<Int> = MutableLiveData()

    fun getRandomNumber() {
        randomNumber.postValue(28)
    }

    fun playGame(number: Int) {
        randomNumber.value?.let { randomNumber ->
            gameStatus.value = when {
                number > randomNumber -> GameStatus.Lower
                number < randomNumber -> GameStatus.Higher
                else -> GameStatus.RightAnswer
            }

            currentValue.postValue(number)
        }
    }
}