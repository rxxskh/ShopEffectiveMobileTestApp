package com.rxs.shopeffectivemobile.presentation.ui.navigation

import com.rxs.shopeffectivemobile.R

sealed class Screen(val label: String?, val icon: Int?, val route: String) {

    object LoginScreen : Screen(label = null, icon = null, route = "login")
    object MainScreen : Screen(label = "Главная", icon = R.drawable.ic_home, route = "main")
    object CatalogScreen : Screen(label = "Каталог", icon = R.drawable.ic_catalog, route = "catalog")
    object CardScreen : Screen(label = "Корзина", icon = R.drawable.ic_bag, route = "card")
    object DiscountScreen : Screen(label = "Акции", icon = R.drawable.ic_discount, route = "discount")
    object ProfileScreen : Screen(label = "Профиль", icon = R.drawable.ic_account, route = "profile")
    object FavoritesScreen : Screen(label = null, icon = null, route = "favorites")
    object ProductScreen : Screen(label = null, icon = null, route = "catalog/{id}") {
        fun passId(id: String): String {
            return "catalog/${id}"
        }
    }
}
