package com.hello.hello_store_service.data.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("promotions")
data class PromotionEntity(
    @Id
    val id: String? = null,
    val storeId: String,
    val title: String,
    val status: Int = 1,
    val rule: PromotionRule,
    val startAt: Long,
    val endAt: Long,
    val productCondition: ProductCondition? = null
)

data class PromotionRule(
    val type: PromotionStatus,
    val minAmount: Long? = null,
    val minQuantity: Int? = null,
    val discountAmount: Long? = null,
    val discountPercent: Float? = null,
    val giftSpuIds: List<String>? = null
)

enum class PromotionStatus {
    Discount, FullReduction, Gift
}

data class ProductCondition(
    val spuIds: List<String>? = null, // 指定商品
    val categoryIds: List<String>? = null, // 指定分类
    val allProducts: Boolean = false      // 全店促销
)



