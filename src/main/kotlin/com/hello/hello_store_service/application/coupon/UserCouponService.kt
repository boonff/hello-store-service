package com.hello.hello_store_service.application.coupon

import com.hello.hello_store_service.data.model.record.UserCouponRecord
import com.hello.hello_store_service.data.service.coupon.UserCouponDataService
import org.springframework.stereotype.Service

@Service
class UserCouponService(
    private val repositoryService: UserCouponDataService,
    private val evaluator: UserCouponEvaluator
) {
    fun fetchByUsername(username: String): List<UserCouponModel> {
        val records: List<UserCouponRecord> = repositoryService.fetchByUsername(username)
        return records.map { evaluator.evaluate(it) }
    }

    fun fetchByCouponId(username: String, couponId: String): UserCouponModel? {
        return repositoryService.fetchByCouponId(username, couponId)?.let { couponRecord ->
            evaluator.evaluate(couponRecord)
        }
    }

    fun fetchByCouponIds(username: String, couponIds: List<String>): List<UserCouponModel> {
        return repositoryService.fetchByCouponIds(username, couponIds).map { couponRecord ->
            evaluator.evaluate(couponRecord)
        }
    }


}