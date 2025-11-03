package com.hello.hello_store_service.web.trans.business.coupon

import com.hello.hello_store_service.data.model.entity.CouponStatus
import com.hello.hello_store_service.data.model.entity.CouponType
import com.hello.hello_store_service.data.model.composite.UserCoupon
import java.time.LocalDateTime

data class CouponDomain(
    val userCoupon: UserCoupon
) {
    //将discount转换为适合前端的格式
    private fun formatDiscount(discount: Float?): Int =
        discount?.let { value ->
            (1 - value).times(10).toInt()
        } ?: 0

    val coupon
        get() = userCoupon.coupon

    val startTime
        get() = userCoupon.receivedAt

    val endTime: LocalDateTime
        get() = startTime.plusDays(coupon.validDays.toLong())

    val status: CouponStatus
        get() = when {
            userCoupon.isUsed -> CouponStatus.UseLess
            LocalDateTime.now().isAfter(endTime) -> CouponStatus.Disabled
            else -> CouponStatus.Default
        }

    val type: CouponType
        get() = coupon.type

    val displayValue: Int
        get() = when (coupon.type) {
            CouponType.PriceOff -> coupon.discount ?: 0
            CouponType.Discount -> formatDiscount(coupon.discountRate)
        }

    val timeLimit
        get() = startTime to endTime

}


