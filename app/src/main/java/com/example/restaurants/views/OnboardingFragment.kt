package com.example.restaurants.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.restaurants.R
import com.example.restaurants.common.Constants
import com.example.restaurants.common.SharedPrefUtils
import com.example.restaurants.databinding.FragmentOnboardingBinding
import com.example.restaurants.viewModel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    lateinit var binding: FragmentOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()
    private var isForLogin: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArgument()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(layoutInflater)
        setupUI()
        setListeners()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getLoginStatusFlow().collect { status ->
                if (status) {
                    saveUsername()
                    findNavController().navigate(R.id.fragmentRestaurantList)
                } else {
                    showToast(getString(R.string.str_invalid_credentials))
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getRegistrationStatusFlow().collect { status ->
                status?.let {
                    if (status) {
                        saveUsername()
                        findNavController().navigate(R.id.fragmentRestaurantList)
                    } else {
                        showToast(getString(R.string.str_user_already_exits))
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun initArgument() {
        isForLogin = arguments?.getBoolean(Constants.IS_FOR_LOGIN) ?: true
    }

    private fun setupUI() {
        with(binding) {
            onboardingTvLabel.text = if (isForLogin) getString(R.string.str_login_yourself) else getString(R.string.str_register_yourself)
            onboardingBtnNext.text = if (isForLogin) getString(R.string.str_login) else getString(R.string.str_register)
        }
    }

    private fun setListeners() {
        with(binding) {
            onboardingBtnNext.setOnClickListener {
                viewModel.onClickNext(
                    onboardingEtUsername.text.toString(),
                    onboardingEtPassword.text.toString(),
                    isForLogin
                )
            }
        }
    }

    private fun saveUsername() {
        SharedPrefUtils.saveString(
            requireContext(),
            SharedPrefUtils.KEY_USER_NAME,
            binding.onboardingEtUsername.text.toString()
        )
    }
}