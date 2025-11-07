package com.hello.hello_store_service.data.model.entity.coupon

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("user_coupons")
data class UserCouponEntity(
    @Id val id: String,
    val username: String,
    val couponId: String,
    val isUsed: Boolean = false,
    val receivedAt: LocalDateTime = LocalDateTime.now(), // 领取时间
)
