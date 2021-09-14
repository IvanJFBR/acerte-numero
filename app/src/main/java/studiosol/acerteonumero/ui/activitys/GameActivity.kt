package studiosol.acerteonumero.ui.activitys

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.Slider
import studiosol.acerteonumero.R
import studiosol.acerteonumero.databinding.ActivityGameBinding
import studiosol.acerteonumero.repository.RandomNumberRepository
import studiosol.acerteonumero.type.FontSizes
import studiosol.acerteonumero.type.GameStatus
import studiosol.acerteonumero.ui.fragments.NumberDisplayFragment
import studiosol.acerteonumero.util.Constants.Companion.CARACTER_LIMIT
import studiosol.acerteonumero.util.SliderDialog
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
        numberDisplayFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.game_menu, menu)
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun setListeners() {
        binding.apply {
            btSendTry.setOnClickListener {
                val inputedText: String = etNumberTry.text.toString()
                if (inputedText.isNotEmpty()) {
                    viewModel.playGame(parseInt(inputedText))
                    //binding.tvNumberSegments.text = inputedText

                    hideKeyboard()
                }
            }

            btNewGame.setOnClickListener {
                newGame()
            }

            etNumberTry.doOnTextChanged { text, _, _, _ ->
                val length = text?.length
                tvInputCount.text = "$length/$CARACTER_LIMIT"
            }
        }
    }

    private fun setObservers() {
        viewModel.randomNumber.observe(this, { response ->
            Log.d("Response", response.number.toString())
        })

        viewModel.gameStatus.observe(this, {
            if (it == GameStatus.Right || it == GameStatus.Error){
                disableGame()
            }

            getGameStatus(it)
        })

        viewModel.sliderValue.observe(this, {
            FontSizes.fromInt(it)?.let { fontsize -> setSegmentsSize(fontsize) }
        })

//        viewModel.currentValue.observe(this, Observer {
//            binding.tvNumberSegments.text = it.toString()
//        })
    }

    private fun numberDisplayFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(binding.containerFragments.id, NumberDisplayFragment()).commit()

        setSegmentsSize(FontSizes.FontSizeStandart)
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

    private fun newGame() {
        viewModel.getRandomNumber()
        resetGame()
    }

    private fun resetGame() {
        binding.apply {
            //tvNumberSegments.text = ""
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

    private fun hideKeyboard() {
        binding.etNumberTry.let {
            if (it.hasFocus()) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.font -> {
                SliderDialog(getSliderValueFromPreferences()).show(supportFragmentManager, "CustomFragment")
                true
            }
            R.id.pallete -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setSegmentsSize(fontSize: FontSizes) {
        viewModel.apply {
            when(fontSize) {
                FontSizes.FontSizeStandart -> {
                    fontSize1.postValue(R.dimen.standart_size_first)
                    fontSize2.postValue(R.dimen.standart_size_second)
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

        saveSliderValueInPreferences(fontSize.value)
    }

    private fun getSliderValueFromPreferences(): Int {
        return this.getPreferences(Context.MODE_PRIVATE).getInt("SliderValue", 0)
    }

    private fun saveSliderValueInPreferences(sliderValue: Int) {
        this.getPreferences(Context.MODE_PRIVATE)
            .edit().putInt("SliderValue", sliderValue)
            .apply()
    }
}