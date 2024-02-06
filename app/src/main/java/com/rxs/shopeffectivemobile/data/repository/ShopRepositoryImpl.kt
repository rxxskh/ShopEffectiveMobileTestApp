package com.rxs.shopeffectivemobile.data.repository

import com.rxs.shopeffectivemobile.data.datasource.local.LocalDataSource
import com.rxs.shopeffectivemobile.data.datasource.local.model.FavoritesData
import com.rxs.shopeffectivemobile.data.datasource.local.model.UserData
import com.rxs.shopeffectivemobile.data.datasource.remote.ShopApi
import com.rxs.shopeffectivemobile.data.datasource.remote.model.ItemData
import com.rxs.shopeffectivemobile.domain.repository.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl(
    private val remoteApi: ShopApi,
    private val localDataSource: LocalDataSource
) : ShopRepository {

    override suspend fun getCatalogItems(): List<ItemData> {
        return remoteApi.getShopData().items
    }

    override suspend fun saveUser(userData: UserData) {
        localDataSource.saveUser(userData)
    }

    override suspend fun getUser(): UserData {
        return localDataSource.getUser()
    }

    override suspend fun saveFavorites(favoritesData: FavoritesData) {
        localDataSource.saveFavorites(favoritesData)
    }

    override suspend fun getFavorites(): FavoritesData {
        return localDataSource.getFavorites()
    }
}