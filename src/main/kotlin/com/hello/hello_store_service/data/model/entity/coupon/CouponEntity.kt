package com.hello.hello_store_service.data.model.entity.coupon

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("coupons")
data class CouponEntity(
    @Id val couponId: String,
    val title: String,
    val description: String,
    val type: CouponType,
    val tag: String?,
    val threshold: Int,     // 使用门槛
    val discount: Int?,      // 减免金额
    val discountRate: Float?,  // 折扣券
    val validDays: Int = 0      // 有效天数
)

enum class CouponType(val code:Int) {
    Discount(1), PriceOff(2)
}