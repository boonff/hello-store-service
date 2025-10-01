package com.hello.hello_store_service.model.entity

import com.hello.hello_store_service.model.common.ProductCondition
import org.springframework.data.mongodb.core.mapping.Document

@Document("promotions")
data class Promotion(
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


