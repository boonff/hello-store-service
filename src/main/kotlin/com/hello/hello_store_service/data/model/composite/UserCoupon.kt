package com.hello.hello_store_service.data.model.composite

import com.hello.hello_store_service.data.model.entity.CouponEntity
import java.time.LocalDateTime

data class UserCoupon(
    val coupon: CouponEntity,
    val isUsed: Boolean,
    val receivedAt: LocalDateTime
)