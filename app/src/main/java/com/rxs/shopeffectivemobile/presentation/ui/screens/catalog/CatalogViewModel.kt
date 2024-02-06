package com.rxs.shopeffectivemobile.presentation.ui.screens.catalog

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rxs.shopeffectivemobile.common.Category
import com.rxs.shopeffectivemobile.common.SortType
import com.rxs.shopeffectivemobile.data.datasource.local.model.FavoritesData
import com.rxs.shopeffectivemobile.data.datasource.remote.model.ItemData
import com.rxs.shopeffectivemobile.domain.usecase.GetCatalogItemsUseCase
import com.rxs.shopeffectivemobile.domain.usecase.GetFavoritesUseCase
import com.rxs.shopeffectivemobile.domain.usecase.SaveFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getCatalogItemsUseCase: GetCatalogItemsUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val saveFavoritesUseCase: SaveFavoritesUseCase
) : ViewModel() {

    var catalog by mutableStateOf(listOf<ItemData>())
        private set
    var sortedCatalog by mutableStateOf(listOf<ItemData>())
        private set
    var selectedCategory by mutableStateOf<Category>(Category.NoneCategory)
        private set
    var selectedSortType by mutableStateOf<SortType>(SortType.RatingSortType)
        private set
    var favorites by mutableStateOf(mutableListOf<ItemData>())
        private set

    init {
        getCatalog()
    }

    fun selectCategory(input: Category) {
        selectedCategory = input
    }

    fun selectSortType(input: SortType) {
        selectedSortType = input
    }

    fun changeFavoriteStatus(itemData: ItemData) {
        if (isFavorite(itemData)) {
            favorites.remove(itemData)
        } else {
            favorites.add(itemData)
        }
        saveFavorites()
        refreshCatalog()
    }

    fun isFavorite(input: ItemData): Boolean = favorites.contains(input)

    private fun refreshCatalog() {
        val savedCatalog = sortedCatalog
        sortedCatalog = emptyList()
        sortedCatalog = savedCatalog
    }

    fun sortCatalog() {
        sortedCatalog = if (selectedCategory == Category.NoneCategory) {
            catalog
        } else {
            catalog.filter { it.tags.contains(selectedCategory.tag) }
        }
        sortedCatalog = when (selectedSortType) {
            SortType.DescPriceSortType -> sortedCatalog.sortedBy { it.price.priceWithDiscount.toInt() }
            SortType.AscPriceSortType -> sortedCatalog.sortedBy { it.price.priceWithDiscount.toInt() }
                .asReversed()

            else -> sortedCatalog.sortedBy { it.feedback?.rating }
        }
    }

    private fun getCatalog() {
        viewModelScope.launch {
            catalog = getCatalogItemsUseCase.invoke()
        }.invokeOnCompletion {
            sortCatalog()
            getFavorites()
        }
    }

    private fun getFavorites() {
        viewModelScope.launch {
            favorites = catalog.filter { getFavoritesUseCase.invoke().ids.contains(it.id) }.toMutableList()
        }.invokeOnCompletion { refreshCatalog() }
    }

    private fun saveFavorites() {
        viewModelScope.launch {
            saveFavoritesUseCase.invoke(FavoritesData(ids = favorites.map { it.id }))
        }
    }
}
