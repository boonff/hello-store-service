package com.hello.hello_store_service.web.view.order

data class ButtonView(
    val primary: Boolean, // 是否是主要按钮，true 表示主要按钮，false 表示次要按钮
    val type: Int,        // 按钮类型编码，用于区分不同操作
    val name: String      // 按钮显示名称，如“取消订单”“再次购买”
)

enum class OrderButtonTypes(val code: Int, val desc: String) {
    PAY(1, "付款"),
    CANCEL(2, "取消订单"),
    CONFIRM(3, "确认收货"),
    APPLY_REFUND(4, "申请售后"),
    VIEW_REFUND(5, "查看退款"),
    COMMENT(6, "评价"),
    DELETE(7, "删除订单"),
    DELIVERY(8, "查看物流"),
    REBUY(9, "再次购买"),
    INVITE_GROUPON(11, "邀请好友拼团")
}
