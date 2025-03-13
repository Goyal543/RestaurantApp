package com.example.restaurants.data.repo

import com.example.restaurants.data.local.dao.DislikedDao
import com.example.restaurants.data.local.entities.DislikedEntity
import javax.inject.Inject

class DislikeRestaurantRepoImpl @Inject constructor(private val dislikedDao: DislikedDao) :
    DislikeRestaurantRepo {

    override suspend fun dislikeRestaurant(
        username: String, restaurantId: String, isDisliked: Boolean
    ): Boolean {
        dislikedDao.dislikeRestaurant(
            DislikedEntity(
                username = username,
                restaurantId = restaurantId,
                disliked = isDisliked
            )
        )
        return true
    }
}