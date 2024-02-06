package com.rxs.shopeffectivemobile.data.datasource.local

import com.rxs.shopeffectivemobile.data.datasource.local.model.FavoritesData
import com.rxs.shopeffectivemobile.data.datasource.local.model.UserData

interface LocalDataSource {

    suspend fun saveUser(userData: UserData)
    suspend fun getUser(): UserData
    suspend fun saveFavorites(favoritesData: FavoritesData)
    suspend fun getFavorites(): FavoritesData
}