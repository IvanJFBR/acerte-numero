package studiosol.acerteonumero.ui.fragments

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
import studiosol.acerteonumero.R
import studiosol.acerteonumero.databinding.FragmentNumberBinding
import studiosol.acerteonumero.databinding.FragmentNumberBindingImpl
import studiosol.acerteonumero.databinding.FragmentNumberDisplayBinding
import studiosol.acerteonumero.viewModel.GameViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "number"

/**
 * A simple [Fragment] subclass.
 * Use the [NumberFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NumberFragment : Fragment() {

    private lateinit var binding: FragmentNumberBinding

    private var number: MutableLiveData<Int> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number.value = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumberBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        setObservers()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NumberFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String?) =
            NumberFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    private fun setObservers() {
        number.observe(viewLifecycleOwner, Observer {
            setNumber(it)
        })
    }

    private fun setNumber(number: Int) {
        when(number) {
            0 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
                }
            }
            1 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_gray)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottom.background = setBackground(R.drawable.segment_display_gray)
                }
            }
            2 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
                }
            }
            3 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
                }
            }
            4 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_gray)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottom.background = setBackground(R.drawable.segment_display_gray)
                }
            }
            5 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_gray)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
                }
            }
            6 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_gray)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
                }
            }
            7 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottom.background = setBackground(R.drawable.segment_display_gray)
                }
            }
            8 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottom.background = setBackground(R.drawable.segment_display_colorfull)
                }
            }
            9 -> {
                binding.apply {
                    segmentUpper.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentUpperLeft.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentMiddle.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                    segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                    segmentBottom.background = setBackground(R.drawable.segment_display_gray)
                }
            }
        }
    }

    private fun setBackground(drawableResource: Int) : Drawable? {
        return ContextCompat.getDrawable(requireContext(), drawableResource)
    }
}