package com.rxs.shopeffectivemobile.domain.usecase

import com.rxs.shopeffectivemobile.common.DispatcherProvider
import com.rxs.shopeffectivemobile.data.datasource.remote.model.ItemData
import com.rxs.shopeffectivemobile.domain.repository.ShopRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCatalogItemsUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun invoke(): List<ItemData> = withContext(dispatcherProvider.io) {
        shopRepository.getCatalogItems()
    }
}