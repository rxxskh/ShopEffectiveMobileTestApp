package com.rxs.shopeffectivemobile.data.datasource.remote.model

data class PriceData(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)