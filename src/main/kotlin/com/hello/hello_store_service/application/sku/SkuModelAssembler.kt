package com.hello.hello_store_service.application.sku

import com.hello.hello_store_service.data.entity.goods.SkuEntity
import com.hello.hello_store_service.data.service.goods.SpecDataService
import com.hello.hello_store_service.data.service.goods.SpuDataService
import org.springframework.stereotype.Service

@Service
class SkuModelAssembler(
    private val specDataService: SpecDataService,
    private val spuDataService: SpuDataService
) {
    fun genSpecDetails(skuEntity: SkuEntity): List<SpecDetail> {
        return skuEntity.specList.mapNotNull { (specId, specValueId) ->
            val specEntity = specDataService.fetchById(specId) ?: return@mapNotNull null
            val specValue = specDataService.fetchSpecValue(specEntity, specValueId) ?: return@mapNotNull null
            SpecDetail.from(specEntity, specValue)
        }
    }

    fun genSkuModel(skuEntity: SkuEntity): SkuModel? {
        if (skuEntity.skuId == null) return null

        return SkuModel(
            skuId = skuEntity.skuId,
            spuId = skuEntity.spuId,
            specList = genSpecDetails(skuEntity),
            stockInfo = skuEntity.stockInfo,
            skuImage = skuEntity.skuImage,
            weight = skuEntity.weight,
            volume = skuEntity.volume,
            profitPrice = skuEntity.profitPrice,
            salePrice = skuEntity.salePrice,
            linePrice = skuEntity.linePrice
        )
    }
}