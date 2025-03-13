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
import com.example.restaurants.databinding.FragmentReviewListBinding
import com.example.restaurants.domain.models.ReviewDomainModel
import com.example.restaurants.viewModel.RestaurantsListViewModel
import com.example.restaurants.viewModel.ReviewsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewListFragment : Fragment() {
    private lateinit var binding: FragmentReviewListBinding
    private lateinit var adapter: ReviewListAdapter
    private val viewModel: ReviewsListViewModel by viewModels()
    private lateinit var restaurantId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArgument()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewListBinding.inflate(layoutInflater)
        viewModel.getListOfReviews(restaurantId)
        setListeners()
        initAdapter()
        initObserver()
        return binding.root
    }

    private fun initArgument() {
        restaurantId = arguments?.getString(Constants.REST_ID) ?: ""
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getReviewsListFlow().collect { restaurantsList ->
                adapter.updateList(restaurantsList)
            }
        }
    }

    private fun initAdapter() {
        adapter = ReviewListAdapter()
        binding.rvReviewsList.adapter = adapter
    }

    private fun setListeners() {
        with(binding) {
            reviewBtnSubmit.setOnClickListener {
                val username =
                    SharedPrefUtils.getString(requireContext(), SharedPrefUtils.KEY_USER_NAME)
                viewModel.addReview(restaurantId,username, reviewEtText.text.toString())
                reviewEtText.text?.clear()
            }
        }
    }
}