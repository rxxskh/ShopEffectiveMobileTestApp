package com.rxs.shopeffectivemobile.common

sealed class Category(val tag: String, val description: String) {
    object NoneCategory : Category(tag = "none", description = "Смотреть все")
    object FaceCategory : Category(tag = "face", description = "Лицо")
    object BodyCategory : Category(tag = "body", description = "Тело")
    object SuntanCategory : Category(tag = "suntan", description = "Загар")
    object MaskCategory : Category(tag = "mask", description = "Маски")
}
