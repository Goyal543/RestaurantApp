package com.example.restaurants.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurants.data.local.dao.DislikedDao
import com.example.restaurants.data.local.dao.OnboardingDao
import com.example.restaurants.data.local.dao.RestaurantDao
import com.example.restaurants.data.local.dao.ReviewDao
import com.example.restaurants.data.local.entities.DislikedEntity
import com.example.restaurants.data.local.entities.RestaurantEntity
import com.example.restaurants.data.local.entities.ReviewEntity
import com.example.restaurants.data.local.entities.User

@Database(
    entities = [User::class, RestaurantEntity::class, ReviewEntity::class, DislikedEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun onboardingDao(): OnboardingDao

    abstract fun restaurantDao(): RestaurantDao

    abstract fun reviewDao(): ReviewDao

    abstract fun dislikedDao(): DislikedDao
}