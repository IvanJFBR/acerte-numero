package studiosol.acerteonumero.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import studiosol.acerteonumero.model.RandomNumber
import studiosol.acerteonumero.repository.RandomNumberRepository
import studiosol.acerteonumero.type.GameStatus

class GameViewModel(private val repository: RandomNumberRepository) : ViewModel() {

    class Factory(private val repository: RandomNumberRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GameViewModel(repository) as T
        }
    }

    val randomNumber : MutableLiveData<RandomNumber> = MutableLiveData()
    var gameStatus : GameStatus = GameStatus.Normal

    fun getRandomNumber() {
        viewModelScope.launch {
            randomNumber.value = repository.getRandomNumber()
        }
    }

    fun playGame(number: Int) {
        randomNumber.value?.let {
            gameStatus = when {
                number > it.number -> GameStatus.Lower
                number < it.number -> GameStatus.Higher
                else -> GameStatus.Right
            }
        }
    }
}