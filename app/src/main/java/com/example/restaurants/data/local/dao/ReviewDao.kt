package com.example.restaurants.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.restaurants.data.local.entities.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Insert
    suspend fun insertReview(reviewEntity: ReviewEntity)

    @Query("SELECT * FROM reviews WHERE restaurantId = :restaurantId")
    fun getListOfReviews(restaurantId: String): Flow<List<ReviewEntity>?>
}