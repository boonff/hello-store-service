package com.hello.hello_store_service.model.common

enum class PriceType(val code: Int) {
    SALE(1),         // 销售价
    LINE(2),         // 划线价
    MEMBER(3),       // 会员价
    PROMOTION(4)     // 活动价
}