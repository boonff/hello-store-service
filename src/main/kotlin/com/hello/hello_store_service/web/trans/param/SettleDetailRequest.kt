package com.hello.hello_store_service.web.trans.param

data class SettleDetailRequest(
    val skuList: List<SkuData>,
    val couponIdList: List<CouponRef>? = null,  // 优惠券
    val userAddressId: String,
)

data class CouponRef(
    val couponId: String,               // 优惠券 ID
    val storeId: String?                 // 对应店铺 ID
)

data class SkuData(
    val skuId: String,
    val quantity: Int
)