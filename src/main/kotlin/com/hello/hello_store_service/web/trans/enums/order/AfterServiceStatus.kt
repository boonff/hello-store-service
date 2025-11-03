package com.hello.hello_store_service.web.trans.enums.order

enum class AfterServiceStatus(val code: Int, val desc: String) {
    TO_AUDIT(10, "待审核"),
    THE_APPROVED(20, "已审核"),
    HAVE_THE_GOODS(30, "已收货"),
    ABNORMAL_RECEIVING(40, "收货异常"),
    COMPLETE(50, "已完成"),
    CLOSED(60, "已关闭")
}
