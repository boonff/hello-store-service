package com.hello.hello_store_service.web.trans.enums.order

enum class ServiceReceiptStatus(val code: Int, val desc: String) {
    RECEIPTED(1, "已收到货"),
    NOT_RECEIPTED(2, "未收到货")
}
