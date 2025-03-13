package com.example.restaurants.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, val restaurantId: String, val review: String , val reviewBy: String
)