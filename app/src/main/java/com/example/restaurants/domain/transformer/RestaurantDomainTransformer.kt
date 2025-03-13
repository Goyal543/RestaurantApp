package com.example.restaurants.domain.transformer

import com.example.restaurants.data.local.entities.RestaurantEntity
import com.example.restaurants.domain.models.RestaurantDomainModel

fun RestaurantEntity.toDomainModel(isDisliked: Boolean): RestaurantDomainModel {
    return RestaurantDomainModel(
        restaurantId = this.restaurantId,
        name = this.restaurantName,
        address = this.restaurantAddress,
        categories = this.restaurantCategories,
        isDisliked = isDisliked
    )
}