package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.clean.CouponClean
import com.hello.hello_store_service.web.view.coupon.CouponResultList
import com.hello.hello_store_service.web.view.coupon.UserCouponView
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupon")
class CouponController(
    private val couponClean: CouponClean,
) {
    private val logger: Logger = LoggerFactory.getLogger(CouponController::class.java)

    @GetMapping("/user")
    fun getUserCoupon(): List<UserCouponView> {
        val uid = SecurityUtils.fetchUid()
        return couponClean.fetchUserCouponViews(uid)
    }

    @GetMapping("/user/{status}")
    fun getStatusCoupon(@PathVariable status: String): List<UserCouponView> {
        val uid = SecurityUtils.fetchUid()
        return couponClean.fetchUserCouponViews(uid).filter { it.status == status }
    }

    @GetMapping("/user/result")
    fun getCouponResultList(): CouponResultList {
        val uid = SecurityUtils.fetchUid()
        return couponClean.fetchCouponResultList(uid)
    }
}
