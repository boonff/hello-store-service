package com.hello.hello_store_service.application.coupon.model

import com.hello.hello_store_service.data.model.entity.coupon.CouponEntity
import com.hello.hello_store_service.data.model.entity.coupon.CouponType
import java.time.LocalDateTime

data class CouponModel(
    val couponId: String,
    val title: String,
    val description: String,
    val type: CouponType,
    val tag: String?,
    val threshold: Int,
    val couponValue: Float,
    val receivedAt: LocalDateTime,
    val failureAt: LocalDateTime,
    val status: CouponStatus
) {
    companion object {
        fun build(
            couponEntity: CouponEntity,
            couponValue: Float,
            receivedAt: LocalDateTime,
            failureAt: LocalDateTime,
            status: CouponStatus
        ): CouponModel {
            return CouponModel(
                couponId = couponEntity.couponId,
                title = couponEntity.title,
                description = couponEntity.description,
                type = couponEntity.type,
                tag = couponEntity.tag,
                threshold = couponEntity.threshold,
                couponValue = couponValue,
                receivedAt = receivedAt,
                failureAt = failureAt,
                status = status
            )
        }
    }
}

enum class CouponStatus(val typeName: String, val code: Int) {
    Default("default", 0),
    UseLess("useless", 1),
    Disabled("disabled", 2),
}