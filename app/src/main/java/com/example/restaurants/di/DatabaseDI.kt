package com.example.restaurants.di

import android.content.Context
import androidx.room.Room
import com.example.restaurants.data.local.dao.OnboardingDao
import com.example.restaurants.data.local.dao.RestaurantDao
import com.example.restaurants.data.local.AppDatabase
import com.example.restaurants.data.local.dao.DislikedDao
import com.example.restaurants.data.local.dao.ReviewDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseDI {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "restaurant_database"
        ).build()
    }

    @Provides
    fun provideOnboardingDao(database: AppDatabase): OnboardingDao {
        return database.onboardingDao()
    }

    @Provides
    fun provideRestaurantDao(database: AppDatabase): RestaurantDao {
        return database.restaurantDao()
    }

    @Provides
    fun provideReviewDao(database: AppDatabase): ReviewDao {
        return database.reviewDao()
    }

    @Provides
    fun provideDislikedDao(database: AppDatabase): DislikedDao {
        return database.dislikedDao()
    }

}