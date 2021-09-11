package studiosol.acerteonumero.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import studiosol.acerteonumero.model.RandomNumber
import studiosol.acerteonumero.repository.RandomNumberRepository

class GameViewModel(private val repository: RandomNumberRepository) : ViewModel() {

    class Factory(private val repository: RandomNumberRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GameViewModel(repository) as T
        }
    }

    val randomNumber : MutableLiveData<RandomNumber> = MutableLiveData()

    fun getRandomNumber() {
        viewModelScope.launch {
            val response = repository.getRandomNumber()
            randomNumber.value = response
        }
    }
}