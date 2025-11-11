package com.hello.hello_store_service.application.coupon.rule

import com.hello.hello_store_service.application.coupon.model.CouponModel
import org.springframework.stereotype.Component

@Component
class DiscountCouponRule : CouponRule() {
    override fun calculateDiscount(userCoupon: CouponModel, originalPrice: Int): Int {
        if (!isUsable(userCoupon, originalPrice)) return 0
        return (originalPrice * userCoupon.couponValue).toInt()
    }
}