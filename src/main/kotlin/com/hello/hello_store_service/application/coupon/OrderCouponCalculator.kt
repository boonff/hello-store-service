package com.hello.hello_store_service.application.coupon

import org.springframework.stereotype.Service


@Service
class OrderCouponCalculator(
    private val userCouponService: UserCouponService,
    private val userCouponCalculator: UserCouponCalculator
) {

    fun discountCoupons(
        username: String,
        couponIds: List<String>,
        originalPrice: Int
    ): Int {
        val userCoupons = fetchUserCoupons(username, couponIds)
        return calculateTotalDiscount(userCoupons, originalPrice)
    }

    private fun calculateTotalDiscount(userCoupons: List<UserCouponModel>, originalPrice: Int): Int {
        return userCoupons.sumOf { userCoupon ->
            userCouponCalculator.calculateDiscount(userCoupon, originalPrice)
        }
    }

    private fun fetchUserCoupons(username: String, couponIds: List<String>): List<UserCouponModel> =
        userCouponService.fetchByCouponIds(username, couponIds)
}