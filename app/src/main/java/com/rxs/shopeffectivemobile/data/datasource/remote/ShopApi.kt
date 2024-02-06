package com.rxs.shopeffectivemobile.data.datasource.remote

import com.rxs.shopeffectivemobile.data.datasource.remote.model.ShopData
import retrofit2.http.GET

interface ShopApi {

    @GET("v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getShopData(): ShopData
}