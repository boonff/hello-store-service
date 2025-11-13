package com.hello.hello_store_service.web.view.order

data class PaymentView(
    val payStatus: Int,          // 支付状态，1 表示已支付，0 表示未支付等
    val amount: String,          // 支付金额（分为单位）
    val currency: String?,       // 币种，如 CNY、USD 等
    val payType: String?,        // 支付类型，例如线上支付、线下支付
    val payWay: String?,         // 支付方式编码，例如微信、支付宝
    val payWayName: String?,     // 支付方式名称，例如“微信支付”“支付宝”
    val interactId: String?,     // 支付交互ID
    val traceNo: String?,        // 支付流水号
    val channelTrxNo: String?,   // 渠道交易号
    val period: String?,         // 支付周期或分期信息
    val payTime: String?,        // 支付发起时间
    val paySuccessTime: String?  // 支付成功时间
)
