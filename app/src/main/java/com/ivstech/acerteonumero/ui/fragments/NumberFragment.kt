package com.ivstech.acerteonumero.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ivstech.acerteonumero.R
import com.ivstech.acerteonumero.databinding.FragmentNumberBinding
import com.ivstech.acerteonumero.viewModel.GameViewModel

private const val NUMBER_PARAM = "number"

class NumberFragment : Fragment() {

    private lateinit var fragmentNumberBinding: FragmentNumberBinding
    private lateinit var viewModel: GameViewModel

    private var number: MutableLiveData<Int> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number.value = it.getInt(NUMBER_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentNumberBinding = FragmentNumberBinding.inflate(inflater, container, false)
        fragmentNumberBinding.lifecycleOwner = viewLifecycleOwner

        return fragmentNumberBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createViewModel()
    }

    override fun onResume() {
        super.onResume()

        setNumber(null)
        setObservers()
    }

    companion object {
        @JvmStatic
        fun newInstance(number: String?) =
            NumberFragment().apply {
                arguments = Bundle().apply {
                    putString(NUMBER_PARAM, number)
                }
            }
    }

    private fun createViewModel() {
        this.viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        fragmentNumberBinding.viewModel = viewModel
    }

    private fun setObservers() {
        number.observe(viewLifecycleOwner, {
            setNumber(it)
        })

        viewModel.apply {
            fontSize1.observe(viewLifecycleOwner, {
                fragmentNumberBinding.size1 = resources.getDimension(it)
            })

            fontSize2.observe(viewLifecycleOwner, {
                fragmentNumberBinding.size2 = resources.getDimension(it)
            })
        }
    }

    private fun setNumber(number: Int?) {
        when (number) {
            0 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentMiddle.background = setBackground(R.drawable.segment_display_gray)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
            }
            1 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_gray)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentMiddle.background = setBackground(R.drawable.segment_display_gray)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentBottom.background = setBackground(R.drawable.segment_display_gray)
            }
            2 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_gray)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
            }
            3 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
            }
            4 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_gray)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentBottom.background = setBackground(R.drawable.segment_display_gray)
            }
            5 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_gray)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
            }
            6 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_gray)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
            }
            7 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentMiddle.background = setBackground(R.drawable.segment_display_gray)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentBottom.background = setBackground(R.drawable.segment_display_gray)
            }
            8 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
            }
            9 -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
            }
            else -> fragmentNumberBinding.apply {
                segmentUpper.background = setBackground(R.drawable.segment_display_gray)
                segmentUpperRight.background = setBackground(R.drawable.segment_display_gray)
                segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentMiddle.background = setBackground(R.drawable.segment_display_gray)
                segmentBottomRight.background = setBackground(R.drawable.segment_display_gray)
                segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                segmentBottom.background = setBackground(R.drawable.segment_display_gray)
            }
        }
    }

    private fun setBackground(drawableResource: Int): Drawable? {
        return ContextCompat.getDrawable(requireContext(), drawableResource)
    }
}