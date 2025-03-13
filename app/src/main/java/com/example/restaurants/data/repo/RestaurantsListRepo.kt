package com.example.restaurants.data.repo

import com.example.restaurants.domain.models.RestaurantDomainModel
import kotlinx.coroutines.flow.Flow

interface RestaurantsListRepo {
    suspend fun getListOfRestaurants(userName: String): Flow<List<RestaurantDomainModel>?>

    suspend fun refreshList()
}