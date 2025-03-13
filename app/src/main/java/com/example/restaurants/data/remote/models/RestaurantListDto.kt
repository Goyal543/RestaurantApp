package com.example.restaurants.data.remote.models

import com.google.gson.annotations.SerializedName

class RestaurantListDto {

    @SerializedName("results")
    var results: ArrayList<RestaurantDto> = ArrayList()
}