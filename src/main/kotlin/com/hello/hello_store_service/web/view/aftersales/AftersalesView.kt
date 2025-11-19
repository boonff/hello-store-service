package com.hello.hello_store_service.web.view.aftersales

data class AftersalesView(
    val buttonVOs: List<ButtonVO>,
    val saasId: String,
    val storeId: String,
    val uid: String,
    val refundMethodList: List<RefundMethod>,
    val createTime: String,
    val rights: RightsView,
    val rightsItem: List<RightsItemView>,
    val rightsRefund: RightsRefundView,
    val logisticsVO: LogisticsView
)

data class ButtonVO(
    val name: String,
    val primary: Boolean,
    val type: Int
)

data class RefundMethod(
    val refundMethodAmount: Long,
    val refundMethodName: String
)
