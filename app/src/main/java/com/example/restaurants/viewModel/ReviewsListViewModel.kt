package com.example.restaurants.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.data.repo.RestaurantsListRepo
import com.example.restaurants.data.repo.ReviewsListRepo
import com.example.restaurants.domain.models.RestaurantDomainModel
import com.example.restaurants.domain.models.ReviewDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsListViewModel @Inject constructor(val reviewsListRepo: ReviewsListRepo) : ViewModel() {

    private var reviewsListFlow: MutableSharedFlow<List<ReviewDomainModel>> = MutableSharedFlow()

    fun getReviewsListFlow(): MutableSharedFlow<List<ReviewDomainModel>> = reviewsListFlow

    fun getListOfReviews(restaurantId: String) {
        viewModelScope.launch {
            reviewsListRepo.getListOfReviews(restaurantId).collect { listOfReviews ->
                if (listOfReviews.isNullOrEmpty()) {

                } else {
                    reviewsListFlow.emit(listOfReviews)
                }
            }
        }
    }

    fun addReview(restaurantId: String, username: String, reviewText: String) {
        viewModelScope.launch {
            reviewsListRepo.addReviews(
                ReviewDomainModel(
                    reviewBy = username, reviewText = reviewText, restaurantId = restaurantId
                )
            )
        }
    }
}