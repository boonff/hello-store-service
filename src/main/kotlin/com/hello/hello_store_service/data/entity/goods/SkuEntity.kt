package com.hello.hello_store_service.data.entity.goods

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("skus")
data class SkuEntity(
    @Id val skuId: String?= null,
    val spuId: String,                       // 所属商品 ID
    val specList: List<SpecRef>,             // SKU 的规格组合
    val stockInfo: StockInfo,                // 库存信息
    val skuImage: String? = null,            // SKU 图
    val weight: Weight? = null,              // 重量
    val volume: Long? = null,                // 体积
    val profitPrice: Int? = null,            // 利润价
    val salePrice: Int,                      // SKU 真实售价
    val linePrice: Int? = null               // 划线价（原价）
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

