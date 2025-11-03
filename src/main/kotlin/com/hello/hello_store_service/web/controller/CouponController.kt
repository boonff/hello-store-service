package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.data.service.coupon.UserCouponService
import com.hello.hello_store_service.util.SecurityUtils
import com.hello.hello_store_service.web.trans.view.coupon.CouponResultList
import com.hello.hello_store_service.web.trans.view.coupon.UserCouponView
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupon")
class CouponController(
    private val couponService: UserCouponService
) {
    private val logger: Logger = LoggerFactory.getLogger(CouponController::class.java)

    @GetMapping("/user")
    fun getUserCoupon(): List<UserCouponView> {
        val username = SecurityUtils.currentUsername()
        return couponService.userCouponViews(username)
    }

    @GetMapping("/user/{status}")
    fun getStatusCoupon(@PathVariable status: String): List<UserCouponView> {
        val username = SecurityUtils.currentUsername()
        return couponService.fetchStatusCoupons(username, status)
    }

    @GetMapping("/user/result")
    fun getCouponResultList(): CouponResultList {
        val username = SecurityUtils.currentUsername()
        return couponService.couponResultList(username)
    }
}
