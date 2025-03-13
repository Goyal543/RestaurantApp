package com.example.restaurants.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurants.data.local.entities.DislikedEntity

@Dao
interface DislikedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun dislikeRestaurant(dislikedEntity: DislikedEntity)

    @Query("SELECT * FROM disliked WHERE restaurantId = :restaurantId AND username = :username")
    fun getDislikedStatus(restaurantId: String, username: String): DislikedEntity?
}