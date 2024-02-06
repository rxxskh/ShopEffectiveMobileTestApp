package com.rxs.shopeffectivemobile.presentation.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rxs.shopeffectivemobile.data.datasource.local.model.UserData
import com.rxs.shopeffectivemobile.domain.usecase.GetFavoritesUseCase
import com.rxs.shopeffectivemobile.domain.usecase.GetUserUseCase
import com.rxs.shopeffectivemobile.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    var user by mutableStateOf(UserData())
        private set
    var favoritesCount by mutableStateOf(0)
        private set

    init {
        getUser()
        getFavoritesCount()
    }

    fun getPhoneNumber(): String {
        return if (user.phoneNumber.length == 10) {
            "+ 7 ${user.phoneNumber.substring(0, 3)} " +
                    "${user.phoneNumber.substring(3, 6)} " +
                    "${user.phoneNumber.substring(6, 8)} " +
                    user.phoneNumber.substring(8, 10)
        } else {
            ""
        }
    }

    fun getFavoritesCountText(): String {
        val lastDigit = favoritesCount % 10
        val lastTwoDigits = favoritesCount % 100
        return favoritesCount.toString() + when {
            lastDigit == 1 && lastTwoDigits != 11 -> " товар"
            lastDigit in 2..4 && lastTwoDigits !in 12..14 -> " товара"
            else -> " товаров"
        }
    }

    fun quit(onComplete: () -> Unit) {
        viewModelScope.launch {
            saveUserUseCase.invoke(UserData())
        }.invokeOnCompletion { onComplete() }
    }

    private fun getUser() {
        viewModelScope.launch {
            user = getUserUseCase.invoke()
        }
    }

    private fun getFavoritesCount() {
        viewModelScope.launch {
            favoritesCount = getFavoritesUseCase.invoke().ids.size
        }
    }
}