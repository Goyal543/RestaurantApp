package com.example.restaurants.data

import com.example.restaurants.data.local.dao.OnboardingDao
import com.example.restaurants.data.local.entities.User
import javax.inject.Inject

class OnboardingUserImpl @Inject constructor(private val onboardingDao: OnboardingDao) :
    OnboardingUser {

    override suspend fun getUserDetails(username: String, password: String): User? {
        return onboardingDao.getUser(username = username, password= password)
    }

    override suspend fun getUserDetails(username: String): User? {
        return onboardingDao.getUser(username = username)
    }

    override suspend fun registerUser(username: String, password: String): Boolean {
        onboardingDao.insertUser(User(username = username, password = password))
        return true
    }
}