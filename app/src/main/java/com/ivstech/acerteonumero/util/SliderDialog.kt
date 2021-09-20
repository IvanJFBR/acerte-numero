package com.ivstech.acerteonumero.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.Slider
import com.ivstech.acerteonumero.R
import com.ivstech.acerteonumero.databinding.SliderDialogLayoutBinding
import com.ivstech.acerteonumero.viewModel.GameViewModel

class SliderDialog(var sliderValue: Int) : DialogFragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: SliderDialogLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SliderDialogLayoutBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        createViewModel()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        setDialogListeners(sliderValue)
    }

    private fun createViewModel() {
        this.viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
    }

    private fun setDialogListeners(value: Int) {
        binding.apply {
            slider.value = value.toFloat()
            slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    viewModel.sliderValue.value = slider.value.toInt()
                }
            })
        }
    }
}