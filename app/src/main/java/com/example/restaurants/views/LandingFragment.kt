package com.example.restaurants.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.restaurants.R
import com.example.restaurants.common.Constants
import com.example.restaurants.databinding.FragmentLandingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : Fragment() {
    lateinit var binding: FragmentLandingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLandingBinding.inflate(layoutInflater)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        with(binding) {
            landingBtnLogin.setOnClickListener {
                val bundle = Bundle()
                bundle.putBoolean(Constants.IS_FOR_LOGIN, true)
                findNavController().navigate(R.id.fragmentLogin,bundle)
            }

            landingBtnRegister.setOnClickListener {
                val bundle = Bundle()
                bundle.putBoolean(Constants.IS_FOR_LOGIN, false)
                findNavController().navigate(R.id.fragmentLogin,bundle)
            }
        }
    }
}