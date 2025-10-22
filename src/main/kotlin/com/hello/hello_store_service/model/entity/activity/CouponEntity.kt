package com.hello.hello_store_service.model.entity.activity

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
    val threshold: Double?,     // 使用门槛
    val discount: Double?,      // 减免金额
    val discountRate: Double?,  // 折扣券
    val validDays: Int = 0      // 有效天数
)

enum class CouponType {
    Discount, PriceOff
}

@Document("user_coupons")
data class UserCoupon(
    @Id val id: String,
    val username: String,
    val couponId: String,
    val isUsed: Boolean = false,
    val receivedAt: LocalDateTime = LocalDateTime.now(), // 领取时间
)