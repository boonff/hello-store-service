package com.hello.hello_store_service.application.coupon

import com.hello.hello_store_service.data.model.entity.coupon.CouponType
import java.time.LocalDateTime

data class UserCouponModel(
    val couponId: String,
    val title: String,
    val description: String,
    val type: CouponType,
    val tag: String?,
    val threshold: Int,
    val discount: Int?,
    val discountRate: Float?,
    val receivedAt: LocalDateTime,
    val failureAt: LocalDateTime,
    val status: CouponStatus
)

enum class CouponStatus(val typeName: String, val code: Int) {
    Default("default", 0),
    UseLess("useless", 1),
    Disabled("disabled", 2),
}