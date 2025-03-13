package com.example.restaurants.data.repo

import com.example.restaurants.domain.models.ReviewDomainModel
import kotlinx.coroutines.flow.Flow

interface ReviewsListRepo {
    suspend fun getListOfReviews(restaurantId: String): Flow<List<ReviewDomainModel>?>

    suspend fun addReviews(reviewDomainModel: ReviewDomainModel)
}