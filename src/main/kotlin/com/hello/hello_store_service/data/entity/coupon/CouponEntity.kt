package com.hello.hello_store_service.data.entity.coupon

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("coupons")
data class CouponEntity(
    @Id val couponId: String? = null,
    val title: String,
    val description: String,
    val type: CouponType,
    val tag: String? = null,
    val threshold: Int,     // 使用门槛
    val priceOff: Int? = null,      // 减免金额
    val discount: Float? = null,  // 折扣券
    val validDays: Int = 0      // 有效天数
)

enum class CouponType(val code:Int) {
    Discount(2), PriceOff(1)
}