package com.hello.hello_store_service.model.dto.goods

import com.hello.hello_store_service.model.entity.goods.PriceInfo
import com.hello.hello_store_service.model.entity.goods.SpecRef
import com.hello.hello_store_service.model.entity.goods.StockInfo
import com.hello.hello_store_service.model.entity.goods.Weight

/**
 * SKU DTO
 */
data class SkuDTO(
    val skuId: String,
    val spuId: String,
    val specInfo: List<SpecDTO>,             // 对应的规格组合
    val stockInfo: StockInfo,
    val skuImage: String?,
    val weight: Weight?,
    val volume: Long?,
    val minSalePrice: Int?,
    val maxLinePrice: Int?
)
