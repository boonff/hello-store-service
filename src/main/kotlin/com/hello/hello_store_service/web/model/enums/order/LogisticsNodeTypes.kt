package com.hello.hello_store_service.web.model.enums.order

enum class LogisticsNodeTypes(val code: Int, val desc: String) {
    SUBMITTED(200001, "已提交订单"),
    PAYMENTED(200002, "已付款/已下单"),
    SHIPPED(200003, "已发货"),
    CANCELED(200004, "已取消"),
    RECEIVED(200005, "已签收"),
    ADDRESS_CHANGED(200006, "已修改地址"),
    IN_TRANSIT(200007, "运输中")
}
