package com.hello.hello_store_service.web.model.request

import com.hello.hello_store_service.application.order.SkuData

data class OrderRequest(
    val skuList: List<SkuData>,
    val couponIdList: List<String>?,
    val userAddressId: String?,
)

