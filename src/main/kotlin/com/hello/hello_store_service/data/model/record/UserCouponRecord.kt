package com.hello.hello_store_service.data.model.record

import com.hello.hello_store_service.data.model.entity.coupon.CouponEntity
import java.time.LocalDateTime

data class UserCouponRecord(
    val coupon: CouponEntity,
    val isUsed: Boolean,
    val receivedAt: LocalDateTime
)