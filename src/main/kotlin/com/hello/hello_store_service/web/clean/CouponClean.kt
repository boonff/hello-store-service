package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.coupon.UserCouponService
import com.hello.hello_store_service.application.coupon.UserCouponModel
import com.hello.hello_store_service.web.model.view.coupon.CouponData
import com.hello.hello_store_service.web.model.view.coupon.CouponResultList
import com.hello.hello_store_service.web.model.view.coupon.CouponView
import com.hello.hello_store_service.web.model.view.coupon.UserCouponView
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class CouponClean(
    private val userCouponService: UserCouponService
) {
    fun fetchUserCouponViews(username: String): List<UserCouponView> {
        return userCouponService.fetchByUsername(username).map { userCoupon ->
            UserCouponView(
                key = userCoupon.couponId,
                status = userCoupon.status.typeName,
                type = userCoupon.type.code,
                value = userCoupon.discount,
                tag = userCoupon.tag,
                desc = userCoupon.description,
                base = userCoupon.threshold,
                title = userCoupon.title,
                timeLimit = "${timeFormatter(userCoupon.receivedAt)} - ${timeFormatter(userCoupon.receivedAt)}",
            )
        }
    }

    fun fetchCouponResultList(username: String): CouponResultList {
        return CouponResultList(
            couponDataList = userCouponService.fetchByUsername(username).map { userCoupon ->
                fetchCouponData(userCoupon)
            },
            reduce = 0
        )
    }


    private fun fetchCouponData(userCoupon: UserCouponModel): CouponData {
        return CouponData(
            couponVO = fetchCouponView(userCoupon),
            status = false
        )
    }

    private fun fetchCouponView(userCoupon: UserCouponModel): CouponView {
        return CouponView(
            storeId = null,
            condition = userCoupon.title,
            couponId = userCoupon.couponId,
            startTime = timeFormatter(userCoupon.receivedAt),
            endTime = timeFormatter(userCoupon.failureAt),
            name = userCoupon.description,
            profit = null,
            promotionCode = null,
            promotionSubCode = null,
            scopeText = null,
            value = userCoupon.discount ?: 0,
            type = userCoupon.type.code
        )
    }

    private fun timeFormatter(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        return "${localDateTime.format(formatter)}"
    }

}