package studiosol.acerteonumero.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import studiosol.acerteonumero.R
import studiosol.acerteonumero.databinding.ActivityGameBinding
import studiosol.acerteonumero.databinding.FragmentNumberDisplayBinding
import studiosol.acerteonumero.databinding.NumberDisplayLayoutBinding
import studiosol.acerteonumero.viewModel.GameViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [NumberDisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NumberDisplayFragment : Fragment() {
    private lateinit var binding: FragmentNumberDisplayBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumberDisplayBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createViewModel()
        setObservers()
    }

    override fun onResume() {
        super.onResume()

        createViewModel()
        setObservers()
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.currentValue.observe(viewLifecycleOwner, Observer {
            setDisplayNumber("101")
        })
    }

    private fun setDisplayNumber(value: String) {
        val numbers = value.map { it.toString().toInt() }

        for (pos: Int in numbers) {
            when(numbers[pos]) {
                0 -> {
                    (binding.displayContainer.getChildAt(pos) as NumberDisplayLayoutBinding).apply {
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
                    (binding.displayContainer.getChildAt(pos) as NumberDisplayLayoutBinding).apply {
                        segmentUpper.background = setBackground(R.drawable.segment_display_gray)
                        segmentUpperRight.background = setBackground(R.drawable.segment_display_colorfull)
                        segmentUpperLeft.background = setBackground(R.drawable.segment_display_gray)
                        segmentMiddle.background = setBackground(R.drawable.segment_display_gray)
                        segmentBottomRight.background = setBackground(R.drawable.segment_display_colorfull)
                        segmentBottomLeft.background = setBackground(R.drawable.segment_display_gray)
                        segmentBottom.background = setBackground(R.drawable.segment_display_gray)
                    }
                }
            }
        }
    }

    private fun setBackground(drawableResource: Int) : Drawable? {
        return ContextCompat.getDrawable(requireContext(), drawableResource)
    }
}