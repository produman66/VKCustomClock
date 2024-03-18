package com.example.customclockview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.customclockview.R
import com.example.customclockview.databinding.FragmentFirstPresentationBinding


class FirstPresentationFragment : Fragment() {

    private var _binding: FragmentFirstPresentationBinding? = null
    private val bindingFP get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstPresentationBinding.inflate(inflater, container, false)

        return bindingFP.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }



}