package com.hello.hello_store_service.web.model.request

import com.hello.hello_store_service.application.settle.SkuData

data class SettleDetailRequest(
    val skuList: List<SkuData>,
    val couponIdList: List<String>? = null,  // 优惠券
    val userAddressId: String?,
)

