package com.example.reference13.farment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.reference13.R
import com.example.reference13.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {
            btnSotuvchilar.setOnClickListener {
                findNavController().navigate(R.id.sotuvchiFragment)
            }
            btnXaridorlar.setOnClickListener {
                findNavController().navigate(R.id.xaridorFragment)
            }
            btnBuyurtmalar.setOnClickListener {
                findNavController().navigate(R.id.buyurtmaFragment )
            }
        }

        return binding.root
        }
}