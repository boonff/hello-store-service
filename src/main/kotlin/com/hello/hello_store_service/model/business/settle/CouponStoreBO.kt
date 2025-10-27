package com.hello.hello_store_service.model.business.settle

import com.hello.hello_store_service.model.entity.activity.CouponEntity


data class CouponStoreBO(
    val coupon: CouponEntity,
    val storeId: String
)

fun CouponEntity.toCouponStoreBO(storeId: String): CouponStoreBO =
    CouponStoreBO(
        coupon = this,
        storeId = storeId
    )