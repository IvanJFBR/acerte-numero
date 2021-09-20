package com.ivstech.acerteonumero.ui.activitys

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.ivstech.acerteonumero.R
import com.ivstech.acerteonumero.databinding.ActivityGameBinding
import com.ivstech.acerteonumero.repository.RandomNumberRepository
import com.ivstech.acerteonumero.type.FontSizes
import com.ivstech.acerteonumero.type.GameStatus
import com.ivstech.acerteonumero.ui.fragments.NumberDisplayFragment
import com.ivstech.acerteonumero.util.Constants.Companion.CARACTER_LIMIT
import com.ivstech.acerteonumero.util.SliderDialog
import com.ivstech.acerteonumero.viewModel.GameViewModel
import java.lang.Integer.parseInt

class GameActivity : AppCompatActivity() {
    private lateinit var activityGameBinding: ActivityGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val randomNumberRepository = RandomNumberRepository()
        val viewModelFactory = GameViewModel.Factory(randomNumberRepository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        viewModel.getRandomNumber()

        activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        setListeners()
        setObservers()
        createNumberDisplayFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.game_menu, menu)
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun setListeners() {
        activityGameBinding.apply {
            btSendTry.setOnClickListener {
                val numberTry: String = etNumberTry.text.toString()
                if (numberTry.isNotEmpty()) {
                    viewModel.playGame(parseInt(numberTry))

                    hideKeyboard()
                }
            }

            btNewGame.setOnClickListener {
                newGame()
            }

            etNumberTry.doOnTextChanged { text, _, _, _ ->
                tvInputCount.text = "${text?.length}/$CARACTER_LIMIT"
            }
        }
    }

    private fun setObservers() {
        viewModel.gameStatus.observe(this, {
            if (it == GameStatus.RightAnswer || it == GameStatus.Error) {
                disableGame()
            }

            setGameStatusText(it)
        })

        viewModel.sliderValue.observe(this, {
            FontSizes.fromInt(it)?.let { fontsize -> setDisplayNumberSize(fontsize) }
        })
    }

    private fun createNumberDisplayFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(activityGameBinding.containerFragments.id, NumberDisplayFragment()).commit()

        setDisplayNumberSize(FontSizes.FontSizeStandard)
    }

    private fun setGameStatusText(gameStatus: GameStatus?) {
        val status = when (gameStatus) {
            GameStatus.Higher -> getString(R.string.tip_higher)
            GameStatus.Lower -> getString(R.string.tip_lower)
            GameStatus.RightAnswer -> getString(R.string.right_answer)
            GameStatus.Error -> getString(R.string.request_error)
            else -> null
        }

        activityGameBinding.tvTryResult.text = status
    }

    private fun newGame() {
        viewModel.getRandomNumber()
        resetGame()
    }

    private fun resetGame() {
        activityGameBinding.apply {
            tvTryResult.text = ""
            etNumberTry.text.clear()
        }

        enableGame()
    }

    private fun enableGame() {
        activityGameBinding.apply {
            btNewGame.visibility = View.GONE
            etNumberTry.isEnabled = true
            btSendTry.isEnabled = true
        }
    }

    private fun disableGame() {
        activityGameBinding.apply {
            btNewGame.visibility = View.VISIBLE
            btSendTry.isEnabled = false
            etNumberTry.isEnabled = false
        }
    }

    private fun hideKeyboard() {
        activityGameBinding.etNumberTry.let {
            if (it.hasFocus()) {
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.font -> {
                SliderDialog(getSliderValueFromPreferences()).show(
                    supportFragmentManager,
                    "CustomFragment"
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setDisplayNumberSize(fontSize: FontSizes) {
        viewModel.apply {
            when (fontSize) {
                FontSizes.FontSizeStandard -> {
                    fontSize1.value = R.dimen.standart_size_first
                    fontSize2.value = R.dimen.standart_size_second
                }
                FontSizes.FontSize1 -> {
                    fontSize1.value = R.dimen.option1_size_first
                    fontSize2.value = R.dimen.option1_size_second
                }
                FontSizes.FontSize2 -> {
                    fontSize1.value = R.dimen.option2_size_first
                    fontSize2.value = R.dimen.option2_size_second
                }
                FontSizes.FontSize3 -> {
                    fontSize1.value = R.dimen.option3_size_first
                    fontSize2.value = R.dimen.option3_size_second
                }
                FontSizes.FontSize4 -> {
                    fontSize1.value = R.dimen.option4_size_first
                    fontSize2.value = R.dimen.option4_size_second
                }
            }
        }

        setSliderValueInPreferences(fontSize.value)
    }

    private fun getSliderValueFromPreferences(): Int {
        return this.getPreferences(Context.MODE_PRIVATE).getInt("SliderValue", 0)
    }

    private fun setSliderValueInPreferences(sliderValue: Int) {
        this.getPreferences(Context.MODE_PRIVATE)
            .edit().putInt("SliderValue", sliderValue)
            .apply()
    }
}