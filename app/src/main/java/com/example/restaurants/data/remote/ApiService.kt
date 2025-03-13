package com.example.restaurants.data.remote

import com.example.restaurants.common.Constants
import com.example.restaurants.data.remote.models.RestaurantListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("v3/places/search")
    suspend fun getListOfRestaurants(
        @Header("Authorization") auth: String = Constants.AUTH_KEY,
        @Query("query") query: String = "restaurant",
        @Query("near") near: String = "bangalore",
        @Query("limit") limit: Int = 20
    ): RestaurantListDto
}