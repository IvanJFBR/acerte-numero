package studiosol.acerteonumero.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import studiosol.acerteonumero.databinding.FragmentNumberDisplayBinding
import studiosol.acerteonumero.viewModel.GameViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [NumberDisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NumberDisplayFragment : Fragment() {

    companion object {
        fun newInstance() = NumberDisplayFragment
    }

    private lateinit var binding: FragmentNumberDisplayBinding
    private lateinit var viewModel: GameViewModel

    private val fragmentToRemove: ArrayList<Fragment> = arrayListOf()

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
            setDisplayNumber(it.toString())
        })
    }

    private fun setDisplayNumber(value: String) {
        val numbers = value.map { it.toString().toInt() }

        for (fragment in fragmentToRemove) {
            childFragmentManager.beginTransaction().remove(fragment).commit()
        }

        for (number: Int in numbers) {
            val fragmentManager: FragmentManager = childFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val fragment = NumberFragment()
            val args = Bundle()
            args.putInt("number", number)
            fragment.arguments = args

            fragmentToRemove.add(fragment)
            fragmentTransaction.add(binding.displayContainer.id, fragment).commit()
        }
    }
}