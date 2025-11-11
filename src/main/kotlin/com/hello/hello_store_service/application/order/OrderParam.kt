package com.hello.hello_store_service.application.order

data class OrderParam(
    val username: String,
    val skuList: List<SkuData>,
    val couponIdList: List<String>? = null
)

data class SkuData(
    val skuId: String,
    val quantity: Int
)
