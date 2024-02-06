package com.rxs.shopeffectivemobile.data.datasource.remote.model

data class ItemData(
    val available: Int,
    val description: String,
    val feedback: FeedbackData?,
    val id: String,
    val info: List<InfoData>,
    val ingredients: String,
    val price: PriceData,
    val subtitle: String,
    val tags: List<String>,
    val title: String
)