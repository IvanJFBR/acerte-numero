package studiosol.acerteonumero.ui.activitys

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import studiosol.acerteonumero.R
import studiosol.acerteonumero.databinding.ActivityGameBinding
import studiosol.acerteonumero.repository.RandomNumberRepository
import studiosol.acerteonumero.type.GameStatus
import studiosol.acerteonumero.ui.fragments.NumberDisplayFragment
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

        setListeners()
        setObservers()
        descriptionFragment()
    }

    private fun setListeners() {
        binding.apply {
            btSendTry.setOnClickListener {
                val inputedText: String = etNumberTry.text.toString()
                if (inputedText.isNotEmpty()) {
                    viewModel.playGame(parseInt(inputedText))
                    binding.tvNumberSegments.text = inputedText
                }
            }

            btNewGame.setOnClickListener {
                newGame()
            }
        }
    }

    private fun newGame() {
        viewModel.getRandomNumber()
        resetGame()
    }

    private fun setObservers() {
        viewModel.randomNumber.observe(this, Observer { response ->
            Log.d("Response", response.number.toString())
        })

        viewModel.gameStatus.observe(this, Observer {
            if (it == GameStatus.Right || it == GameStatus.Error){
                disableGame()
            }

            getGameStatus(it)
        })

        viewModel.currentValue.observe(this, Observer {
            binding.tvNumberSegments.text = it.toString()
        })
    }

    private fun descriptionFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(binding.containerFragments.id, NumberDisplayFragment()).commit()
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

    private fun resetGame() {
        binding.apply {
            tvNumberSegments.text = ""
            tvTryResult.text = ""
            etNumberTry.text.clear()
        }

        enableGame()
    }

    private fun enableGame() {
        binding.apply {
            btNewGame.visibility = View.GONE
            etNumberTry.isEnabled = true
            btSendTry.isEnabled = true
        }
    }

    private fun disableGame() {
        binding.apply {
            btNewGame.visibility = View.VISIBLE
            btSendTry.isEnabled = false
            etNumberTry.isEnabled = false
        }
    }
}