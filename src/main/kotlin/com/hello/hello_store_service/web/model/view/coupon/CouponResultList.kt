package com.hello.hello_store_service.web.model.view.coupon

data class CouponResultList(
    val couponDataList: List<CouponData>,
    val reduce: Int
)

data class CouponData(
    val couponVO: CouponView,
    val status: Boolean
)

data class CouponView(
    val storeId: String?,
    val condition: String,
    val couponId: String,
    val startTime: String,
    val endTime: String,
    val name: String,
    val profit: String?,
    val promotionCode: String?,
    val promotionSubCode: String?,
    val scopeText: String?,
    val value: Int,
    val type: Int,
)