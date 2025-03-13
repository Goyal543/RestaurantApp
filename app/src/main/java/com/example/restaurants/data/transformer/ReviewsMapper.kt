package com.example.restaurants.data.transformer

import com.example.restaurants.data.local.entities.ReviewEntity
import com.example.restaurants.domain.models.ReviewDomainModel

fun ReviewDomainModel.toEntityList(): ReviewEntity {
   return ReviewEntity(
       restaurantId = this.restaurantId,
       review = this.reviewText,
       reviewBy = this.reviewBy
   )
}