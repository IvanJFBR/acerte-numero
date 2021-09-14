package studiosol.acerteonumero.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.slider.Slider
import studiosol.acerteonumero.R
import studiosol.acerteonumero.type.FontSizes
import studiosol.acerteonumero.ui.fragments.NumberFragment

private const val ARG_PARAM1 = "sliderValue"

class SliderDialog : DialogFragment() {
    private var sliderValue: MutableLiveData<Int> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sliderValue.value = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)

        return inflater.inflate(R.layout.slider_dialog_layout, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        setObservers()
    }

    private fun setObservers() {
        sliderValue.observe(viewLifecycleOwner, {
            //setDialogListeners()
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
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


//    private fun setDialogListeners(view: View) {
//        val slider = view.findViewById<Slider>(R.id.slider)
//        slider.value = getSliderValueFromPreferences().toFloat()
//        slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener{
//            override fun onStartTrackingTouch(slider: Slider) {}
//
//            override fun onStopTrackingTouch(slider: Slider) {
//                FontSizes.fromInt(slider.value.toInt())?.let { setSegmentsSize(it) }
//            }
//        })
//    }
}