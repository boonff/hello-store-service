package com.hello.hello_store_service.application.coupon

import com.hello.hello_store_service.application.coupon.model.CouponModel
import com.hello.hello_store_service.application.coupon.model.CouponModelFactory
import com.hello.hello_store_service.data.model.record.UserCouponRecord
import com.hello.hello_store_service.data.service.coupon.UserCouponDataService
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val repositoryService: UserCouponDataService,
) {
    fun fetchCouponModelList(username: String): List<CouponModel> {
        val records: List<UserCouponRecord> = repositoryService.fetchByUsername(username)
        return records.map { CouponModelFactory.build(it) }
    }

    fun fetchCouponModel(username: String, couponId: String): CouponModel? {
        return repositoryService.fetchByCouponId(username, couponId)?.let { couponRecord ->
            CouponModelFactory.build(couponRecord)
        }
    }

    fun fetchByCouponIds(username: String, couponIds: List<String>): List<CouponModel> {
        return repositoryService.fetchByCouponIds(username, couponIds).map { couponRecord ->
            CouponModelFactory.build(couponRecord)
        }
    }


}