package com.example.restaurants.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.data.repo.DislikeRestaurantRepo
import com.example.restaurants.data.repo.DislikeRestaurantRepoImpl
import com.example.restaurants.data.repo.RestaurantsListRepo
import com.example.restaurants.domain.models.RestaurantDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsListViewModel @Inject constructor(
    private val restaurantsListRepo: RestaurantsListRepo,
    private val dislikeRestaurantRepo: DislikeRestaurantRepo
) : ViewModel() {
    private var restaurantsList: List<RestaurantDomainModel> = arrayListOf()
    private var restaurantsListFlow: MutableSharedFlow<List<RestaurantDomainModel>> =
        MutableSharedFlow()

    fun getRestaurantsListFlow(): MutableSharedFlow<List<RestaurantDomainModel>> =
        restaurantsListFlow

    fun getListOfRestaurants(userName: String) {
        viewModelScope.launch {
            restaurantsListRepo.getListOfRestaurants(userName).collect { listOfRestaurants ->
                if (listOfRestaurants.isNullOrEmpty()) {
                    restaurantsListRepo.refreshList()
                } else {
                    restaurantsList = listOfRestaurants
                    restaurantsListFlow.emit(listOfRestaurants)
                }
            }
        }
    }

    fun dislikeRestaurant(userName: String, restaurantId: String, isDisliked: Boolean) {
        viewModelScope.launch {
            dislikeRestaurantRepo.dislikeRestaurant(userName, restaurantId, isDisliked)
            restaurantsList.firstOrNull { it.restaurantId == restaurantId }?.let { restaurant ->
                restaurant.isDisliked = isDisliked
            }
            restaurantsListFlow.emit(restaurantsList)
        }
    }
}