package com.rxs.shopeffectivemobile.common

sealed class SortType(val label: String) {
    object RatingSortType : SortType(label = "По популярности")
    object DescPriceSortType : SortType(label = "По уменьшению цены")
    object AscPriceSortType : SortType(label = "По возрастанию цены")
}
