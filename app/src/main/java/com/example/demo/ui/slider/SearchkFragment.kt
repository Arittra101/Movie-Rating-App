package com.example.demo.ui.slider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController

import com.example.demo.R
import com.example.demo.databinding.FragmentDemoBinding
import com.example.demo.databinding.FragmentSearchkBinding


class SearchkFragment : Fragment(R.layout.fragment_searchk) {

    private lateinit var binding: FragmentSearchkBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchkBinding.bind(view)
        binding.demotxt.setOnClickListener {
            findNavController().navigate(R.id.demoFragment)
        }
    }
}