package com.hello.hello_store_service.web.view.aftersales

data class RightsRefundView(
    val callbackTime: String,
    val channel: String,
    val channelTrxNo: String,
    val createTime: String,
    val refundDesc: String,
    val memo: String,
    val refundAmount: Long,
    val refundStatus: Int,
    val requestTime: String,
    val successTime: String,
    val traceNo: String,
    val updateTime: String
)
