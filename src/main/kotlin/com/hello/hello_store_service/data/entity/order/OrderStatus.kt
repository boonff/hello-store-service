package com.hello.hello_store_service.data.entity.order

enum class OrderStatus(val code: Int, val desc: String) {
    PENDING_PAYMENT(5, "待支付"),
    PENDING_DELIVERY(10, "待发货"),
    DELIVERED(20, "已发货"), // 表示商家已发出
    COMPLETE(50, "已完成"),
    CANCELED(80, "已取消"),
    PAYMENT_TIMEOUT(81, "支付超时取消");

    companion object {
        fun fromCode(code: Int?): OrderStatus? {
            return OrderStatus.entries.firstOrNull { it.code == code }
        }
    }
}
