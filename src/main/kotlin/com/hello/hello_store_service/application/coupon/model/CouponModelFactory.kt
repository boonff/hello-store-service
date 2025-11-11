package com.hello.hello_store_service.application.coupon.model

import com.hello.hello_store_service.data.model.entity.coupon.CouponEntity
import com.hello.hello_store_service.data.model.entity.coupon.CouponType
import com.hello.hello_store_service.data.model.record.UserCouponRecord
import org.springframework.stereotype.Component
import java.time.LocalDateTime


object CouponModelFactory {
    fun build(record: UserCouponRecord): CouponModel {
        val failureAt = record.receivedAt.plusDays(record.coupon.validDays.toLong())

        val status = when {
            record.isUsed -> CouponStatus.UseLess
            LocalDateTime.now().isAfter(failureAt) -> CouponStatus.Disabled
            else -> CouponStatus.Default
        }

        return CouponModel.build(
            couponEntity = record.coupon,
            couponValue = getCountValue(record.coupon),
            receivedAt = record.receivedAt,
            failureAt = failureAt,
            status = status
        )
    }

    private fun getCountValue(couponEntity: CouponEntity): Float {
        return when (couponEntity.type) {
            CouponType.Discount -> couponEntity.discount ?: 0.0f
            CouponType.PriceOff -> (couponEntity.priceOff ?: 0).toFloat()
        }
    }
}


