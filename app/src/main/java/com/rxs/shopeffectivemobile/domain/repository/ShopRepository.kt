package com.rxs.shopeffectivemobile.domain.repository

import com.rxs.shopeffectivemobile.data.datasource.local.model.FavoritesData
import com.rxs.shopeffectivemobile.data.datasource.local.model.UserData
import com.rxs.shopeffectivemobile.data.datasource.remote.model.ItemData

interface ShopRepository {

    suspend fun getCatalogItems(): List<ItemData>
    suspend fun saveUser(userData: UserData)
    suspend fun getUser(): UserData
    suspend fun saveFavorites(favoritesData: FavoritesData)
    suspend fun getFavorites(): FavoritesData
}