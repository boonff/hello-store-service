package com.hello.hello_store_service.web.request

data class SettleOrderRequest(
    val orderId: String? = null,
    val skuList: List<SkuData>,
    val couponIdList: List<String>? = null,
    val userAddressId: String? = null,
    val remark: String? = null
)

data class SkuData(
    val skuId: String,
    val quantity: Int
)