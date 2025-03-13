package com.example.restaurants.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "disliked",
    indices = [Index(value = ["username", "restaurantId"], unique = true)]
)
class DislikedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val restaurantId: String,
    val username: String,
    val disliked: Boolean
)