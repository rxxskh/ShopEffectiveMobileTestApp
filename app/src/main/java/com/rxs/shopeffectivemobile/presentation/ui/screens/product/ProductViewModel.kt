package com.rxs.shopeffectivemobile.presentation.ui.screens.product

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rxs.shopeffectivemobile.data.datasource.local.model.FavoritesData
import com.rxs.shopeffectivemobile.data.datasource.remote.model.ItemData
import com.rxs.shopeffectivemobile.domain.usecase.GetCatalogItemsUseCase
import com.rxs.shopeffectivemobile.domain.usecase.GetFavoritesUseCase
import com.rxs.shopeffectivemobile.domain.usecase.SaveFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getCatalogItemsUseCase: GetCatalogItemsUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val saveFavoritesUseCase: SaveFavoritesUseCase
) : ViewModel() {

    var product by mutableStateOf<ItemData?>(null)
        private set
    var favoriteIds by mutableStateOf((listOf<String>()))
        private set

    init {
        getFavorites()
    }

    fun loadProduct(id: String) {
        viewModelScope.launch {
            product = getCatalogItemsUseCase.invoke().first { it.id == id }
        }
    }

    fun isFavorite(): Boolean {
        return favoriteIds.contains(product!!.id)
    }

    fun changeFavoriteStatus() {
        val newValue = favoriteIds.toMutableList()
        if (isFavorite()) {
            newValue.remove(product!!.id)
        } else {
            newValue.remove(product!!.id)
        }
        favoriteIds = newValue
        saveFavorites()
        refreshCard()
    }

    private fun refreshCard() {
        val savedProduct = product
        product = null
        product = savedProduct
    }

    fun getAvailableText(): String {
        val available = product!!.available
        val lastDigit = available % 10
        val lastTwoDigits = available % 100
        return available.toString() + when {
            lastDigit == 1 && lastTwoDigits != 11 -> " штука"
            lastDigit in 2..4 && lastTwoDigits !in 12..14 -> " штуки"
            else -> " штук"
        }
    }

    fun getFeedbackCountText(): String {
        val count = product!!.feedback!!.count
        val lastDigit = count % 10
        val lastTwoDigits = count % 100
        return count.toString() + when {
            lastDigit == 1 && lastTwoDigits != 11 -> " отзыв"
            lastDigit in 2..4 && lastTwoDigits !in 12..14 -> " отзыва"
            else -> " отзывов"
        }
    }

    fun getIntRating(): Int {
        return product!!.feedback!!.rating.toInt()
    }

    fun getFractalRating(): Int {
        return if (hasFractalRating()) 4 - getIntRating() else 5 - getIntRating()
    }

    fun hasFractalRating(): Boolean {
        return product!!.feedback!!.rating % 1.0 != 0.0
    }

    private fun getFavorites() {
        viewModelScope.launch {
            favoriteIds = getFavoritesUseCase.invoke().ids
        }
    }

    private fun saveFavorites() {
        viewModelScope.launch {
            saveFavoritesUseCase.invoke(FavoritesData(ids = favoriteIds))
        }
    }
}