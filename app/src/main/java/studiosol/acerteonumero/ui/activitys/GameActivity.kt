package studiosol.acerteonumero.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import studiosol.acerteonumero.R
import studiosol.acerteonumero.repository.RandomNumberRepository
import studiosol.acerteonumero.type.GameStatus
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

        val button : Button = findViewById(R.id.bt_send_try);
        button.setOnClickListener {
            viewModel.playGame(5)
            getGameStatus(viewModel.gameStatus)
        }

        setObservers()
    }

    private fun setObservers() {
        viewModel.randomNumber.observe(this, Observer { response ->
            Log.d("Response", response.number.toString())
            Log.d("Response", response.number.toString())
        })
    }

    fun getGameStatus(gameStatus: GameStatus) {

        val text = when (gameStatus) {
            GameStatus.Higher -> getString(R.string.tip_higher)
            GameStatus.Lower -> getString(R.string.tip_lower)
            GameStatus.Right -> getString(R.string.right_answer)
            GameStatus.Error -> getString(R.string.request_error)
            else -> "toma no cu"
        }

        val textView: TextView = findViewById(R.id.tv_try_result)
        textView.text = text
    }
}