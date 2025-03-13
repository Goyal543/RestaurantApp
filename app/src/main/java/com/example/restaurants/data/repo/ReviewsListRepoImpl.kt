package com.example.restaurants.data.repo

import com.example.restaurants.data.local.dao.ReviewDao
import com.example.restaurants.data.transformer.toEntityList
import com.example.restaurants.domain.models.RestaurantDomainModel
import com.example.restaurants.domain.models.ReviewDomainModel
import com.example.restaurants.domain.transformer.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReviewsListRepoImpl @Inject constructor(
    val reviewDao: ReviewDao
) : ReviewsListRepo {

    override suspend fun getListOfReviews(restaurantId: String): Flow<List<ReviewDomainModel>?> {
        return reviewDao.getListOfReviews(restaurantId).map { reviewsList ->
            reviewsList?.map { it.toDomainModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addReviews(reviewDomainModel: ReviewDomainModel) {
        reviewDao.insertReview(reviewDomainModel.toEntityList())
    }
}