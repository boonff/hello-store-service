package com.hello.hello_store_service.data.entity.order

enum class ServiceStatus(val code: Int, val desc: String) {
    PENDING_VERIFY(100, "待审核"),
    VERIFIED(110, "已审核待寄回商品"),
    PENDING_DELIVERY(120, "等待买家寄回商品"),
    PENDING_RECEIPT(130, "已寄回商品，待收货"),
    RECEIVED(140, "已收货"),
    EXCEPTION(150, "收货异常"),
    REFUNDED(160, "已退款"),
    CLOSED(170, "已关闭")
}
