package com.hello.hello_store_service.application.goods

import com.hello.hello_store_service.data.model.entity.StoreEntity
import com.hello.hello_store_service.data.service.StoreDataService
import com.hello.hello_store_service.data.service.goods.SkuDataService
import org.springframework.stereotype.Service

@Service
class SkuService(
    private val skuDataService: SkuDataService,
    private val storeDataService: StoreDataService,
    private val goodsAssembler: GoodsAssembler
) {
    fun fetchStoreId(skuId: String): String? {
        return skuDataService.fetchById(skuId)?.storeId
    }

    fun fetchStore(skuId: String): StoreEntity? {
        return skuDataService.fetchById(skuId)?.let { skuEntity ->
            storeDataService.fetchStoreById(skuEntity.storeId)
        }
    }

    fun fetchSkuModel(skuId: String): SkuModel? {
        val skuEntity = skuDataService.fetchById(skuId) ?: return null
        return goodsAssembler.assembleSkuModel(skuEntity)
    }
}