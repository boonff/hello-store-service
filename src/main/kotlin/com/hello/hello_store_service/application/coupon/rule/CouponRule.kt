package com.hello.hello_store_service.application.coupon.rule

import com.hello.hello_store_service.application.coupon.model.CouponStatus
import com.hello.hello_store_service.application.coupon.model.CouponInstance
import com.hello.hello_store_service.data.entity.coupon.CouponType
import org.springframework.stereotype.Component
import java.time.LocalDateTime

abstract class CouponRule {
    abstract fun calculateDiscount(userCoupon: CouponInstance, originalPrice: Int): Int

    fun isUsable(userCoupon: CouponInstance, originalPrice: Int): Boolean {
        return checkStatus(userCoupon) &&
                checkTime(userCoupon) &&
                checkPrice(userCoupon, originalPrice)
    }

    private fun checkStatus(userCoupon: CouponInstance): Boolean {
        return userCoupon.status == CouponStatus.Default
    }

    private fun checkTime(userCoupon: CouponInstance): Boolean {
        val now = LocalDateTime.now()
        return now.isBefore(userCoupon.failureAt)
    }

    private fun checkPrice(userCoupon: CouponInstance, originalPrice: Int): Boolean {
        return originalPrice >= (userCoupon.threshold)
    }
}

@Component
class CouponRuleFactory(
    private val discountCouponRule: DiscountCouponRule,
    private val priceOffCouponRule: PriceOffCouponRule
) {
    fun build(couponType: CouponType): CouponRule {
        return when (couponType) {
            CouponType.Discount -> discountCouponRule
            CouponType.PriceOff -> priceOffCouponRule
        }
    }
}