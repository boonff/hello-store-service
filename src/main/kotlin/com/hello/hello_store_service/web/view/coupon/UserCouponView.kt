package com.hello.hello_store_service.web.view.coupon


data class UserCouponView(
    val key: String,
    val status: String,
    val type: Int,
    val value: Float?,
    val tag: String?,
    val desc: String,
    val base: Int?,
    val title: String,
    val timeLimit: String,
    val currency: String = "¥"
)