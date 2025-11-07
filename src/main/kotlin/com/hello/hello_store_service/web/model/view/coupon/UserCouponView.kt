package com.hello.hello_store_service.web.model.view.coupon


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
)