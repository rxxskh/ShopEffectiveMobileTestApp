package com.rxs.shopeffectivemobile.data.datasource.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.rxs.shopeffectivemobile.common.FAVORITES_DATA_KEY
import com.rxs.shopeffectivemobile.common.USER_DATA_KEY
import com.rxs.shopeffectivemobile.data.datasource.local.model.FavoritesData
import com.rxs.shopeffectivemobile.data.datasource.local.model.UserData
import javax.inject.Inject

class SharedPreferencesSource(
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences
) : LocalDataSource {

    override suspend fun saveUser(userData: UserData) {
        with(sharedPreferences.edit()) {
            val json: String = gson.toJson(userData)
            putString(USER_DATA_KEY, json)
            apply()
        }
    }

    override suspend fun getUser(): UserData {
        val json: String? = sharedPreferences.getString(USER_DATA_KEY, null)
        return if (json != null) {
            gson.fromJson(json, UserData::class.java)
        } else {
            UserData()
        }
    }

    override suspend fun saveFavorites(favoritesData: FavoritesData) {
        with(sharedPreferences.edit()) {
            val json: String = gson.toJson(favoritesData)
            putString(FAVORITES_DATA_KEY, json)
            apply()
        }
    }

    override suspend fun getFavorites(): FavoritesData {
        val json: String? = sharedPreferences.getString(FAVORITES_DATA_KEY, null)
        return if (json != null) {
            gson.fromJson(json, FavoritesData::class.java)
        } else {
            FavoritesData()
        }
    }
}