package com.rxs.shopeffectivemobile.domain.usecase

import com.rxs.shopeffectivemobile.common.DispatcherProvider
import com.rxs.shopeffectivemobile.data.datasource.local.model.UserData
import com.rxs.shopeffectivemobile.data.repository.ShopRepositoryImpl
import com.rxs.shopeffectivemobile.domain.repository.ShopRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun invoke(userData: UserData) = withContext(dispatcherProvider.io) {
        shopRepository.saveUser(userData)
    }
}