package com.hello.hello_store_service.application.coupon

import com.hello.hello_store_service.application.coupon.model.CouponInstance
import com.hello.hello_store_service.application.coupon.model.CouponModelFactory
import com.hello.hello_store_service.data.service.coupon.UserCouponDataService
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val userCouponDataService: UserCouponDataService,
) {
    fun fetchCouponModelList(uid: String): List<CouponInstance> {
        val models = userCouponDataService.fetchByUid(uid)
        return models.mapNotNull { CouponModelFactory.build(it) }
    }

    fun fetchCouponModel(uid: String, couponId: String): CouponInstance? {
        return userCouponDataService.fetchById(uid, couponId)?.let { couponRecord ->
            CouponModelFactory.build(couponRecord)
        }
    }

    fun fetchByCouponIds(uid: String, couponIds: List<String>): List<CouponInstance> {
        return userCouponDataService.fetchByIds(uid, couponIds).mapNotNull { couponRecord ->
            CouponModelFactory.build(couponRecord)
        }
    }


}