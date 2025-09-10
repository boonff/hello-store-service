package com.hello.hello_store_service.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("goods")
data class Goods(
    @Id
    val id: String? = null,
    val saasId: String,
    val storeId: String,
    val spuId: String,
    val title: String,
    val primaryImage: String,
    val images: List<String>,
    val video: String? = null,
    val available: Int,
    val minSalePrice: Int,
    val minLinePrice: Int,
    val maxSalePrice: Int,
    val maxLinePrice: Int,
    val spuStockQuantity: Int,
    val soldNum: Int,
    val isPutOnSale: Int,
    val categoryIds: List<String>,
    val specList: List<Spec>,
    val skuList: List<Sku>,
    val spuTagList: List<SpuTag>,
    val limitInfo: List<LimitInfo>,
    val desc: List<String>,
    val etitle: String
)

data class Spec(
    val specId: String,
    val title: String,
    val specValueList: List<SpecValue>
)

data class SpecValue(
    val specValueId: String,
    val specId: String? = null,
    val saasId: String? = null,
    val specValue: String,
    val image: String? = null
)

data class Sku(
    val skuId: String,
    val skuImage: String,
    val specInfo: List<SpecInfo>,
    val priceInfo: List<PriceInfo>,
    val stockInfo: StockInfo,
    val weight: Weight,
    val volume: Any? = null,
    val profitPrice: Int? = null
)

data class SpecInfo(
    val specId: String,
    val specTitle: String? = null,
    val specValueId: String,
    val specValue: String? = null
)

data class PriceInfo(
    val priceType: Int,
    val price: Int,
    val priceTypeName: String? = null
)

data class StockInfo(
    val stockQuantity: Int,
    val safeStockQuantity: Int,
    val soldQuantity: Int
)

data class Weight(
    val value: Any? = null,
    val unit: String
)

data class SpuTag(
    val id: String,
    val title: String,
    val image: String? = null
)

data class LimitInfo(
    val text: String
)
