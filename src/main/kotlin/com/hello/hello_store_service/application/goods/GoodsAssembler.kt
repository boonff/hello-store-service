package com.hello.hello_store_service.application.goods

import com.hello.hello_store_service.data.entity.goods.SkuEntity
import com.hello.hello_store_service.data.service.goods.SpecDataService
import org.springframework.stereotype.Service

@Service
class GoodsAssembler(
    private val specDataService: SpecDataService,
) {
    fun assembleSpecList(skuEntity: SkuEntity): List<SpecDetail> {
        return skuEntity.specList.mapNotNull { (specId, specValueId) ->
            val specEntity = specDataService.fetchById(specId) ?: return@mapNotNull null
            val specValue = specDataService.fetchSpecValue(specEntity, specValueId) ?: return@mapNotNull null
            SpecDetail.from(specEntity, specValue)
        }
    }

    fun assembleSkuModel(skuEntity: SkuEntity): SkuModel? {
        if (skuEntity.skuId == null) return null

        return SkuModel(
            skuId = skuEntity.skuId,
            spuId = skuEntity.spuId,
            specList = assembleSpecList(skuEntity),
            stockInfo = skuEntity.stockInfo,
            skuImage = skuEntity.skuImage,
            weight = skuEntity.weight,
            volume = skuEntity.volume,
            profitPrice = skuEntity.profitPrice,
            salePrice = skuEntity.salePrice,
            linePrice = skuEntity.linePrice,
        )
    }
}