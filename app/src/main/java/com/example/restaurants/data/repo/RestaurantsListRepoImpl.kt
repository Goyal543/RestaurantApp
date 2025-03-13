package com.example.restaurants.data.repo

import com.example.restaurants.data.local.dao.DislikedDao
import com.example.restaurants.data.local.dao.RestaurantDao
import com.example.restaurants.data.remote.ApiService
import com.example.restaurants.data.transformer.toEntityList
import com.example.restaurants.domain.models.RestaurantDomainModel
import com.example.restaurants.domain.transformer.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantsListRepoImpl @Inject constructor(
    val restaurantDao: RestaurantDao,
    val dislikedDao: DislikedDao,
    val apiService: ApiService
) : RestaurantsListRepo {

    override suspend fun getListOfRestaurants(userName: String): Flow<List<RestaurantDomainModel>?> {
        return restaurantDao.getListOfRestaurants()
            .map { entityList ->
                entityList?.map { restaurantEntity ->
                    val isDisliked =
                        dislikedDao.getDislikedStatus(restaurantEntity.restaurantId, userName)
                    restaurantEntity.toDomainModel(isDisliked?.disliked ?: false)
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun refreshList() {
        val response = apiService.getListOfRestaurants()
        restaurantDao.saveListOfRestaurants(response.toEntityList())
    }
}