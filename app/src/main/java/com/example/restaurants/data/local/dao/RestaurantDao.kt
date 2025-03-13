package com.example.restaurants.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.restaurants.data.local.entities.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert
    suspend fun saveListOfRestaurants(restaurants: List<RestaurantEntity>)

    @Query("SELECT * FROM restaurant")
    fun getListOfRestaurants(): Flow<List<RestaurantEntity>?>
}