package com.hello.hello_store_service.web.trans.view.order

data class ButtonView(
    val primary: Boolean, // 是否是主要按钮，true 表示主要按钮，false 表示次要按钮
    val type: Int,        // 按钮类型编码，用于区分不同操作
    val name: String      // 按钮显示名称，如“取消订单”“再次购买”
)
