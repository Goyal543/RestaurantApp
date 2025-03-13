package com.example.restaurants.data.repo


interface DislikeRestaurantRepo {
    suspend fun dislikeRestaurant(
        username: String,
        restaurantId: String,
        isDisliked: Boolean
    ): Boolean
}