package com.rxs.shopeffectivemobile.presentation.ui.screens.favorites

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
class FavoritesViewModel @Inject constructor(
    private val getCatalogItemsUseCase: GetCatalogItemsUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val saveFavoritesUseCase: SaveFavoritesUseCase
) : ViewModel() {

    var favorites by mutableStateOf(listOf<ItemData>())
        private set


    init {
        getFavorites()
    }

    fun removeFromFavorites(input: ItemData) {
        val newValue = favorites.toMutableList()
        newValue.remove(input)
        favorites = newValue
        saveFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            favorites =
                getCatalogItemsUseCase.invoke().filter { getFavoritesUseCase.invoke().ids.contains(it.id) }
        }
    }

    private fun saveFavorites() {
        viewModelScope.launch {
            saveFavoritesUseCase.invoke(FavoritesData(ids = favorites.map { it.id }))
        }
    }
}