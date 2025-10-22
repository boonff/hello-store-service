package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.view.activity.UserCouponView
import com.hello.hello_store_service.service.activity.CouponService
import com.hello.hello_store_service.util.SecurityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupon")
class CouponController(
    private val couponService: CouponService
) {

    private val logger: Logger = LoggerFactory.getLogger(CouponController::class.java)

    @GetMapping("/user")
    fun getUserCoupon(): List<UserCouponView> {
        val username = SecurityUtils.currentUsername()
        return couponService.getUserCoupons(username)
    }

    @GetMapping("/user/{status}")
    fun getStatusCoupon(@PathVariable status: String): List<UserCouponView> {
        val username = SecurityUtils.currentUsername()
        return couponService.getStatusCoupons(username, status)
    }
}
