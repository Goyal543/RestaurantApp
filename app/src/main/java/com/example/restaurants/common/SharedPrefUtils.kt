package com.example.restaurants.common

import android.content.Context
import android.content.SharedPreferences

object SharedPrefUtils {

    private const val PREF_NAME = "restaurant_pref"
    const val KEY_USER_NAME = "user_name"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveString(context: Context, key: String, value: String) {
        getPreferences(context).edit().putString(key, value).apply()
    }

    fun getString(context: Context, key: String, defaultValue: String = ""): String {
        return getPreferences(context).getString(key, defaultValue) ?: defaultValue
    }

    // Remove a key from SharedPreferences
    fun removeKey(context: Context, key: String) {
        getPreferences(context).edit().remove(key).apply()
    }

    // Clear all SharedPreferences data
    fun clearPreferences(context: Context) {
        getPreferences(context).edit().clear().apply()
    }
}
