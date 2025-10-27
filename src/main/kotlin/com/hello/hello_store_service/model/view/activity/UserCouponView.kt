package com.hello.hello_store_service.model.view.activity

import com.hello.hello_store_service.model.business.activity.CouponBO
import java.time.format.DateTimeFormatter

data class UserCouponView(
    val key: String,
    val status: String,
    val type: Int,
    val value: Double?,
    val tag: String?,
    val desc: String,
    val base: Double?,
    val title: String,
    val timeLimit: String,
    val currency: String = "¥"
) {
    companion object {
        fun from(bo: CouponBO): UserCouponView {
            val (start, end) = bo.getTimeLimit()
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            val timeLimit = "${start.format(formatter)} - ${end.format(formatter)}"

            return UserCouponView(
                key = bo.coupon.couponId,
                status = bo.getStatus(),
                type = bo.getType(),
                value = bo.getValue(),
                tag = bo.coupon.tag,
                desc = bo.coupon.description,
                base = bo.coupon.threshold.toDouble(),
                title = bo.coupon.title,
                timeLimit = timeLimit,
            )
        }
    }
}