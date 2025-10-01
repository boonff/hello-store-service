package com.hello.hello_store_service.model.common

data class ProductCondition(
    val spuIds: List<String>? = null, // 指定商品
    val categoryIds: List<String>? = null, // 指定分类
    val allProducts: Boolean = false      // 全店促销
)
