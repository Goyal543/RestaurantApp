package com.example.restaurants.domain.models

data class RestaurantDomainModel(
    val restaurantId: String,
    val name: String,
    val address: String,
    val categories: String,
    var isDisliked: Boolean
)