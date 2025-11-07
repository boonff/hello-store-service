package com.hello.hello_store_service.application.coupon

import com.hello.hello_store_service.data.model.entity.coupon.CouponType
import org.springframework.stereotype.Component
import java.time.LocalDateTime

interface UserCouponCalculator {
    fun calculateDiscount(userCoupon: UserCouponModel, originalPrice: Int): Int
    fun isUsable(userCoupon: UserCouponModel, originalPrice: Int): Boolean
}

@Component
class DefaultUserCouponCalculator : UserCouponCalculator {
    override fun calculateDiscount(userCoupon: UserCouponModel, originalPrice: Int): Int {
        if (!isUsable(userCoupon, originalPrice)) return 0

        return when (userCoupon.type) {
            CouponType.Discount -> userCoupon.discount ?: 0
            CouponType.PriceOff -> ((originalPrice * (userCoupon.discountRate ?: 0f))
                .toInt()).coerceAtLeast(0)
        }
    }

    override fun isUsable(userCoupon: UserCouponModel, originalPrice: Int): Boolean {
        return checkStatus(userCoupon) &&
                checkTime(userCoupon) &&
                checkPrice(userCoupon, originalPrice)
    }

    private fun checkStatus(userCoupon: UserCouponModel): Boolean {
        return userCoupon.status == CouponStatus.Default
    }

    private fun checkTime(userCoupon: UserCouponModel): Boolean {
        val now = LocalDateTime.now()
        return now.isBefore(userCoupon.failureAt)
    }

    private fun checkPrice(userCoupon: UserCouponModel, originalPrice: Int): Boolean {
        return originalPrice >= (userCoupon.threshold)
    }

}
