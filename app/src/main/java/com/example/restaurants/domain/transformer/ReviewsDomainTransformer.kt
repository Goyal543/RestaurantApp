package com.example.restaurants.domain.transformer

import com.example.restaurants.data.local.entities.RestaurantEntity
import com.example.restaurants.data.local.entities.ReviewEntity
import com.example.restaurants.domain.models.RestaurantDomainModel
import com.example.restaurants.domain.models.ReviewDomainModel

fun ReviewEntity.toDomainModel(): ReviewDomainModel {
    return ReviewDomainModel(
        reviewBy = this.reviewBy, reviewText = this.review , restaurantId = this.restaurantId
    )
}