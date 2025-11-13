package com.hello.hello_store_service.application.coupon.rule

import com.hello.hello_store_service.application.coupon.model.CouponInstance
import org.springframework.stereotype.Component

@Component
class PriceOffCouponRule : CouponRule() {
    override fun calculateDiscount(userCoupon: CouponInstance, originalPrice: Int): Int {
        if (!isUsable(userCoupon, originalPrice)) return 0
        return userCoupon.couponValue.toInt()
    }
}