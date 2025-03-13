package com.example.restaurants.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.restaurants.R
import com.example.restaurants.common.Constants
import com.example.restaurants.common.SharedPrefUtils
import com.example.restaurants.databinding.FragmentLandingBinding
import com.example.restaurants.databinding.FragmentRestaurantListBinding
import com.example.restaurants.viewModel.RestaurantsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantListFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantListBinding
    private lateinit var adapter: RestaurantListAdapter
    private val viewModel: RestaurantsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantListBinding.inflate(layoutInflater)
        viewModel.getListOfRestaurants(getUsername())
        initAdapter()
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getRestaurantsListFlow().collect { restaurantsList ->
                adapter.updateList(restaurantsList)
            }
        }
    }

    private fun initAdapter() {
        adapter = RestaurantListAdapter(onItemClick = { restaurantDetails ->
            val bundle = Bundle().apply {
                putString(Constants.REST_ID, restaurantDetails.restaurantId)
            }
            findNavController().navigate(R.id.fragmentReviewList, bundle)
        }, onDislikeClick = { restaurantDetails ->
            viewModel.dislikeRestaurant(
                getUsername(),
                restaurantDetails.restaurantId,
                restaurantDetails.isDisliked.not()
            )
        })
        binding.rvRestaurantsList.adapter = adapter
    }

    private fun getUsername(): String {
        return SharedPrefUtils.getString(requireContext(), SharedPrefUtils.KEY_USER_NAME, "")
    }
}