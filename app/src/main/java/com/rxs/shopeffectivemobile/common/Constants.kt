package com.rxs.shopeffectivemobile.common

import com.rxs.shopeffectivemobile.R

const val BASE_URL = "https://run.mocky.io/"

const val PREFS_KEY = "shop_prefs"
const val USER_DATA_KEY = "shop_user_data"
const val FAVORITES_DATA_KEY = "shop_favorites_data"


data class Image(
    val id: String,
    val images: List<Int>
)

val IMAGES = listOf(
    Image(id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50", images = listOf(R.drawable.image_6, R.drawable.image_5)),
    Image(id = "54a876a5-2205-48ba-9498-cfecff4baa6e", images = listOf(R.drawable.image_1, R.drawable.image_2)),
    Image(id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3", images = listOf(R.drawable.image_5, R.drawable.image_6)),
    Image(id = "16f88865-ae74-4b7c-9d85-b68334bb97db", images = listOf(R.drawable.image_3, R.drawable.image_5)),
    Image(id = "26f88856-ae74-4b7c-9d85-b68334bb97db", images = listOf(R.drawable.image_2, R.drawable.image_3)),
    Image(id = "15f88865-ae74-4b7c-9d81-b78334bb97db", images = listOf(R.drawable.image_6, R.drawable.image_1)),
    Image(id = "88f88865-ae74-4b7c-9d81-b78334bb97db", images = listOf(R.drawable.image_5, R.drawable.image_3)),
    Image(id = "55f58865-ae74-4b7c-9d81-b78334bb97db", images = listOf(R.drawable.image_1, R.drawable.image_5)),
)