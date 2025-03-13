package com.example.restaurants.di

import com.example.restaurants.data.OnboardingUser
import com.example.restaurants.data.OnboardingUserImpl
import com.example.restaurants.data.local.dao.DislikedDao
import com.example.restaurants.data.local.dao.OnboardingDao
import com.example.restaurants.data.local.dao.RestaurantDao
import com.example.restaurants.data.local.dao.ReviewDao
import com.example.restaurants.data.remote.ApiService
import com.example.restaurants.data.repo.DislikeRestaurantRepo
import com.example.restaurants.data.repo.DislikeRestaurantRepoImpl
import com.example.restaurants.data.repo.RestaurantsListRepo
import com.example.restaurants.data.repo.RestaurantsListRepoImpl
import com.example.restaurants.data.repo.ReviewsListRepo
import com.example.restaurants.data.repo.ReviewsListRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryDI {

    @Provides
    @Singleton
    fun provideOnboardingUserRepo(onboardingDao: OnboardingDao): OnboardingUser {
        return OnboardingUserImpl(onboardingDao)
    }

    @Provides
    @Singleton
    fun provideRestaurantRepo(
        restaurantDao: RestaurantDao,dislikedDao: DislikedDao, apiService: ApiService
    ): RestaurantsListRepo {
        return RestaurantsListRepoImpl(restaurantDao,dislikedDao, apiService)
    }

    @Provides
    @Singleton
    fun provideReviewRepo(
        reviewDao: ReviewDao
    ): ReviewsListRepo {
        return ReviewsListRepoImpl(reviewDao)
    }

    @Provides
    @Singleton
    fun provideDislikeRepo(
        dislikedDao: DislikedDao
    ): DislikeRestaurantRepo {
        return DislikeRestaurantRepoImpl(dislikedDao)
    }
}