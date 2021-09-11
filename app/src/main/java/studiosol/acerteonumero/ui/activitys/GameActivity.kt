package studiosol.acerteonumero.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import studiosol.acerteonumero.R
import studiosol.acerteonumero.repository.RandomNumberRepository
import studiosol.acerteonumero.viewModel.GameViewModel

class GameActivity : AppCompatActivity() {
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val randomNumberRepository = RandomNumberRepository()
        val viewModelFactory = GameViewModel.Factory(randomNumberRepository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        viewModel.getRandomNumber()

        setObservers()
    }

    private fun setObservers() {
        viewModel.randomNumber.observe(this, Observer { response ->
            Log.d("Response", response.number.toString())
        })
    }
}