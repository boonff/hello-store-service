package com.hello.hello_store_service.data.model

import com.hello.hello_store_service.data.entity.coupon.CouponEntity
import java.time.LocalDateTime

data class UserCouponModel(
    val coupon: CouponEntity,
    val isUsed: Boolean,
    val receivedAt: LocalDateTime
)