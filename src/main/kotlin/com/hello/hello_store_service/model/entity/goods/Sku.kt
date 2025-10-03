package com.hello.hello_store_service.model.entity.goods

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("skus")
data class Sku(
    @Id
    val id: String? = null,
    val skuId: String,                       // SKU ID
    val spuId: String,                       // 所属商品 ID
    val specInfo: List<SpecRef>,             // SKU 的规格组合
    val stockInfo: StockInfo,                // 库存信息
    val skuImage: String? = null,            // SKU 图
    val weight: Weight? = null,              // 重量
    val volume: Long? = null,                 // 体积
    val profitPrice: Int? = null,             // 利润价
    val minSalePrice: Int? = null,            // SKU 真实售价（可选缓存参考）
    val maxLinePrice: Int? = null             // 划线价（原价）
)

/**
 * SKU 引用的规格值（只存 ID，避免冗余）
 */
data class SpecRef(
    val specId: String,
    val specValueId: String
)

data class StockInfo(
    val stockQuantity: Int,                  // 库存
    val safeStockQuantity: Int,              // 安全库存
    val soldQuantity: Int                    // 已售
)

data class Weight(
    val value: Double,                       // 重量值
    val unit: String                         // 单位
)

