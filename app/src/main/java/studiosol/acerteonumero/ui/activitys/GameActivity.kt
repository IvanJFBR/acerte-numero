package studiosol.acerteonumero.ui.activitys

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import studiosol.acerteonumero.R
import studiosol.acerteonumero.databinding.ActivityGameBinding
import studiosol.acerteonumero.repository.RandomNumberRepository
import studiosol.acerteonumero.type.GameStatus
import studiosol.acerteonumero.viewModel.GameViewModel
import java.lang.Integer.parseInt

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val randomNumberRepository = RandomNumberRepository()
        val viewModelFactory = GameViewModel.Factory(randomNumberRepository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        viewModel.getRandomNumber()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        setListeneres()
        setObservers()
    }

    private fun setListeneres() {
        binding.apply {
            btSendTry.setOnClickListener {
                val inputedText = etNumberTry.text.toString()
                viewModel.playGame(parseInt(inputedText))
                getGameStatus(viewModel.gameStatus.value)
                binding.tvNumberSegments.text = inputedText
            }

            btNewGame.setOnClickListener {
                newGame()
            }
        }
    }

    private fun newGame() {
        viewModel.getRandomNumber()
        binding.apply {
            tvNumberSegments.text = ""
            tvTryResult.text = ""
            etNumberTry.text.clear()
        }
    }

    private fun setObservers() {
        viewModel.randomNumber.observe(this, Observer { response ->
            Log.d("Response", response.number.toString())
        })

        viewModel.gameStatus.observe(this, Observer {
            binding.btNewGame.visibility = if (it == GameStatus.Right || it == GameStatus.Error){
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }

    private fun getGameStatus(gameStatus: GameStatus?) {
        val status = when (gameStatus) {
            GameStatus.Higher -> getString(R.string.tip_higher)
            GameStatus.Lower -> getString(R.string.tip_lower)
            GameStatus.Right -> getString(R.string.right_answer)
            GameStatus.Error -> getString(R.string.request_error)
            else -> null
        }

        binding.tvTryResult.text = status
    }
}