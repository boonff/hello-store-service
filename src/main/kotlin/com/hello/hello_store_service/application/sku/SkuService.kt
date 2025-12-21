package com.hello.hello_store_service.application.sku

import com.hello.hello_store_service.data.service.goods.SkuDataService
import org.springframework.stereotype.Service

@Service
class SkuService(
    private val skuDataService: SkuDataService,
    private val goodsAssembler: SkuModelAssembler
) {
    fun fetchSkuModel(skuId: String): SkuModel? {
        val skuEntity = skuDataService.fetchById(skuId) ?: return null
        return goodsAssembler.genSkuModel(skuEntity)
    }
}