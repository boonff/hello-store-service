package com.hello.hello_store_service.application.settle

data class SettleParam(
    val username: String,
    val skuList: List<SkuData>,
    val couponIdList: List<String>? = null
)

data class SkuData(
    val skuId: String,
    val quantity: Int
)
