package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.coupon.CouponService
import com.hello.hello_store_service.application.coupon.model.CouponInstance
import com.hello.hello_store_service.data.entity.coupon.CouponType
import com.hello.hello_store_service.web.view.coupon.CouponData
import com.hello.hello_store_service.web.view.coupon.CouponResultList
import com.hello.hello_store_service.web.view.coupon.CouponView
import com.hello.hello_store_service.web.view.coupon.UserCouponView
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class CouponClean(
    private val userCouponService: CouponService
) {
    fun fetchUserCouponViews(uid: String): List<UserCouponView> {
        return userCouponService.fetchCouponModelList(uid).map { userCoupon ->
            UserCouponView(
                key = userCoupon.couponId,
                status = userCoupon.status.typeName,
                type = userCoupon.type.code,
                value = getValue(userCoupon.couponValue, userCoupon.type),
                tag = userCoupon.tag,
                desc = userCoupon.description,
                base = userCoupon.threshold,
                title = userCoupon.title,
                timeLimit = "${timeFormatter(userCoupon.receivedAt)} - ${timeFormatter(userCoupon.receivedAt)}",
            )
        }
    }

    fun fetchCouponResultList(uid: String): CouponResultList {
        return CouponResultList(
            couponDataList = userCouponService.fetchCouponModelList(uid).map { userCoupon ->
                fetchCouponData(userCoupon)
            }, reduce = 0
        )
    }

    fun fetchCouponData(userCoupon: CouponInstance): CouponData {
        return CouponData(
            couponVO = fetchCouponView(userCoupon), status = false
        )
    }

    private fun getValue(couponValue: Float, type: CouponType): Float {
        return when (type) {
            CouponType.PriceOff -> couponValue
            CouponType.Discount -> (1 - couponValue) * 10
        }
    }

    private fun fetchCouponView(userCoupon: CouponInstance): CouponView {
        return CouponView(
            condition = userCoupon.title,
            couponId = userCoupon.couponId,
            startTime = timeFormatter(userCoupon.receivedAt),
            endTime = timeFormatter(userCoupon.failureAt),
            name = userCoupon.description,
            profit = null,
            promotionCode = null,
            promotionSubCode = null,
            scopeText = null,
            value = getValue(userCoupon.couponValue, userCoupon.type),
            type = userCoupon.type.code
        )
    }

    private fun timeFormatter(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        return "${localDateTime.format(formatter)}"
    }

}