package com.hello.hello_store_service.web.request

data class CartRequest(
    val spuId: String,               // 商品 SPU ID
    val skuId: String,               // 商品 SKU ID（对应具体规格）
    val count: Int,                  // 购买数量
)