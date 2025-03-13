package com.example.restaurants.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val restaurantId: String,
    val restaurantName: String,
    val restaurantAddress: String,
    val restaurantCategories: String,
)