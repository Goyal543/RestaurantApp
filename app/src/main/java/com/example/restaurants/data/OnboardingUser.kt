package com.example.restaurants.data

import com.example.restaurants.data.local.entities.User


interface OnboardingUser {
    suspend fun getUserDetails(username: String, password: String): User?
    suspend fun getUserDetails(username: String): User?
    suspend fun registerUser(username: String, password: String): Boolean
}