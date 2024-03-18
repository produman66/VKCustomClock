package com.example.customclockview.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.customclockview.R
import com.example.customclockview.databinding.FragmentSecondPresentationBinding

class SecondPresentationFragment : Fragment() {

    private var _binding: FragmentSecondPresentationBinding? = null
    private val bindingFP get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondPresentationBinding.inflate(inflater, container, false)

        return bindingFP.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Clock Setting No. 2
        bindingFP.clockCustom2.clockColor = ContextCompat.getColor(requireContext(), R.color.beige)

        bindingFP.clockCustom2.clockBorderColor = Color.RED

        bindingFP.clockCustom2.minuteDotsSize = 0f
        bindingFP.clockCustom2.minuteDotsColor = Color.RED

        bindingFP.clockCustom2.digitColor = Color.RED

        bindingFP.clockCustom2.secondHandColor = Color.RED
        bindingFP.clockCustom2.minuteHandColor = Color.RED
        bindingFP.clockCustom2.hourHandColor = Color.RED

        //Clock Setting No. 3
        bindingFP.clockCustom3.clockColor = ContextCompat.getColor(requireContext(), R.color.beige)

        bindingFP.clockCustom3.clockBorderColor = ContextCompat.getColor(
            requireContext(),
            R.color.beige
        )

        bindingFP.clockCustom3.minuteDotsSize = 0f

        bindingFP.clockCustom3.digitSize = 50f
        bindingFP.clockCustom3.digitColor = Color.RED

        bindingFP.clockCustom3.secondHandColor = Color.RED
        bindingFP.clockCustom3.minuteHandColor = Color.RED
        bindingFP.clockCustom3.hourHandColor = Color.RED


        //Clock Setting No. 4
        bindingFP.clockCustom4.clockRadius = 50f
        bindingFP.clockCustom4.clockColor = ContextCompat.getColor(requireContext(), R.color.beige)

        bindingFP.clockCustom4.clockBorderColor = ContextCompat.getColor(
            requireContext(),
            R.color.beige
        )

        bindingFP.clockCustom4.digitSize = 0f

        bindingFP.clockCustom4.secondHandSize = 0f

        //Clock Setting No. 5
        bindingFP.clockCustom5.clockColor = Color.TRANSPARENT

        bindingFP.clockCustom5.clockBorderColor = Color.BLACK
        bindingFP.clockCustom5.clockBorderWidth = 20f

        bindingFP.clockCustom5.minuteDotsSize = 0f

        bindingFP.clockCustom5.digitColor = Color.WHITE

        bindingFP.clockCustom5.minuteHandColor = Color.RED

        bindingFP.clockCustom5.secondHandSize = 0f

    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}