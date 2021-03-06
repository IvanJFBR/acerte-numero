package com.ivstech.acerteonumero.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ivstech.acerteonumero.databinding.FragmentNumberDisplayBinding
import com.ivstech.acerteonumero.viewModel.GameViewModel

class NumberDisplayFragment : Fragment() {

    private lateinit var numberDisplayBinding: FragmentNumberDisplayBinding
    private lateinit var viewModel: GameViewModel

    private val fragmentToRemove: ArrayList<Fragment> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        numberDisplayBinding = FragmentNumberDisplayBinding.inflate(inflater, container, false)
        numberDisplayBinding.lifecycleOwner = viewLifecycleOwner

        return numberDisplayBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createViewModel()
        setObservers()
        resetDisplay()
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
        viewModel.currentValue.observe(viewLifecycleOwner, {
            setDisplay(it)
        })

        viewModel.randomNumber.observe(viewLifecycleOwner, {
            resetDisplay()
        })

        viewModel.apply {
            fontSize1.observe(viewLifecycleOwner, {
                updateDisplay()
            })

            fontSize2.observe(viewLifecycleOwner, {
                updateDisplay()
            })
        }
    }

    private fun updateDisplay() {
        viewModel.currentValue.value.let {
            if (it == null) resetDisplay()
            else setDisplay(it)
        }
    }

    private fun resetDisplay() {
        removeAllFragments()

        val fragmentManager: FragmentManager = childFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = NumberFragment()

        fragmentToRemove.add(fragment)
        fragmentTransaction.add(numberDisplayBinding.displayContainer.id, fragment).commit()
    }

    private fun setDisplay(value: Int) {
        val numbers = value.toString().map { it.toString().toInt() }

        removeAllFragments()

        for (number: Int in numbers) {
            val fragmentManager: FragmentManager = childFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val fragment = NumberFragment()
            val args = Bundle()
            args.putInt("number", number)
            fragment.arguments = args

            fragmentToRemove.add(fragment)
            fragmentTransaction.add(numberDisplayBinding.displayContainer.id, fragment).commit()
        }
    }

    private fun removeAllFragments() {
        for (fragment in fragmentToRemove) {
            childFragmentManager.beginTransaction().remove(fragment).commit()
        }
    }
}