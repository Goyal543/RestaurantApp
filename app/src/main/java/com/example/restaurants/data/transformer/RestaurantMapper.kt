package com.example.restaurants.data.transformer

import com.example.restaurants.data.local.entities.RestaurantEntity
import com.example.restaurants.data.remote.models.RestaurantListDto

fun RestaurantListDto.toEntityList(): List<RestaurantEntity> {
    return this.results.map { restaurant ->
        RestaurantEntity(
            restaurantId = restaurant.restaurantId,
            restaurantName = restaurant.name,
            restaurantAddress = restaurant.location.formattedAddress,
            restaurantCategories = restaurant.categories.joinToString { it.name }
        )
    }
}