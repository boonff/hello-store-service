package com.hello.hello_store_service.application.coupon

import com.hello.hello_store_service.application.coupon.model.CouponInstance
import com.hello.hello_store_service.application.coupon.model.CouponModelFactory
import com.hello.hello_store_service.data.service.coupon.UserCouponDataService
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val userCouponDataService: UserCouponDataService,
) {
    fun fetchCouponModelList(username: String): List<CouponInstance> {
        val models = userCouponDataService.fetchByUid(username)
        return models.mapNotNull { CouponModelFactory.build(it) }
    }

    fun fetchCouponModel(username: String, couponId: String): CouponInstance? {
        return userCouponDataService.fetchById(username, couponId)?.let { couponRecord ->
            CouponModelFactory.build(couponRecord)
        }
    }

    fun fetchByCouponIds(username: String, couponIds: List<String>): List<CouponInstance> {
        return userCouponDataService.fetchByIds(username, couponIds).mapNotNull { couponRecord ->
            CouponModelFactory.build(couponRecord)
        }
    }


}