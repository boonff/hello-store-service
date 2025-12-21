package com.hello.hello_store_service.web.request

data class OrderRequest(
    val pageSize: Int,
    val pageNum: Int,
    val orderStatus: Int?,
)