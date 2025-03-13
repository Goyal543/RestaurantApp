package com.example.restaurants.data.remote.models

import com.google.gson.annotations.SerializedName

data class RestaurantDto(
    @SerializedName("fsq_id") val restaurantId: String,
    val name: String,
    val location: RestaurantLocationDto,
    val categories: List<Category>
)

data class Category(
    val id : String,
    val name : String,
    val short_name : String,
    val plural_name : String,
    val icon : Icon,
)

data class Icon(
    val prefix : String,
    val suffix : String,
)

data class RestaurantLocationDto(
    @SerializedName(
        "formatted_address", alternate = arrayOf("address")
    ) val formattedAddress: String,
)