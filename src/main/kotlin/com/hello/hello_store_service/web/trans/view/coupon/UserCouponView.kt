package com.hello.hello_store_service.web.trans.view.coupon

import com.hello.hello_store_service.web.trans.business.coupon.CouponDomain
import java.time.format.DateTimeFormatter

data class UserCouponView(
    val key: String,
    val status: String,
    val type: Int,
    val value: Int?,
    val tag: String?,
    val desc: String,
    val base: Int?,
    val title: String,
    val timeLimit: String,
    val currency: String = "¥"
) {
    companion object {
        fun from(bo: CouponDomain): UserCouponView {
            val (start, end) = bo.timeLimit
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            val timeLimit = "${start.format(formatter)} - ${end.format(formatter)}"

            return UserCouponView(
                key = bo.coupon.couponId,
                status = bo.status.typeName,
                type = bo.type.code,
                value = bo.displayValue,
                tag = bo.coupon.tag,
                desc = bo.coupon.description,
                base = bo.coupon.threshold,
                title = bo.coupon.title,
                timeLimit = timeLimit,
            )
        }
    }
}