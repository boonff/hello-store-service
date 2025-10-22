package com.hello.hello_store_service.model.enums.order

enum class ServiceType(val code: Int, val desc: String) {
    RETURN_GOODS(10, "退货"),
    ONLY_REFUND(20, "退款"),
    ORDER_CANCEL(30, "支付后取消")
}
