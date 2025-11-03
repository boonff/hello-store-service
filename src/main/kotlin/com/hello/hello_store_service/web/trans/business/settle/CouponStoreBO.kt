package com.hello.hello_store_service.web.trans.business.settle

import com.hello.hello_store_service.data.model.entity.CouponEntity


data class CouponStoreBO(
    val coupon: CouponEntity,
    val storeId: String?
)

fun CouponEntity.toCouponStoreBO(storeId: String?): CouponStoreBO =
    CouponStoreBO(
        coupon = this,
        storeId = storeId
    )