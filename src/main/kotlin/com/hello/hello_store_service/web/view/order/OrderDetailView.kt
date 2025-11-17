package com.hello.hello_store_service.web.view.order

data class OrderDetailView(
    val uid: String,                          // 用户ID
    val orderId: String,                      // 订单ID
    val orderStatus: Int,                     // 订单状态
    val totalAmount: Int,                  // 订单总金额（分）
    val goodsAmount: Int,                  // 商品金额（分）
    val goodsAmountApp: Int,               // 商品金额（应用计算）（分）
    val paymentAmount: Int,                // 实付金额（分）
    val freightFee: Int,                   // 运费（分）
    val discountAmount: Int,               // 折扣金额（分）

    val remark: String,                       // 订单备注
    val cancelType: Int?,                  // 取消类型
    val cancelReasonType: Int?,            // 取消原因类型
    val cancelReason: String?,                // 取消原因
    val rightsType: Int?,                  // 维权类型
    val createTime: Long,                   // 订单创建时间（时间戳）
    val orderItemVOs: List<OrderItemView>,    // 商品列表
    val logisticsVO: LogisticsView,           // 物流信息
    val paymentVO: PaymentView,               // 支付信息
    val buttonVOs: List<ButtonView>?,          // 订单操作按钮列表
    val labelVOs: List<Any>?,                 // 标签列表
    val invoiceVO: Any?,                      // 发票信息
    val couponAmount: Int?,                // 优惠券金额（分）
    val autoCancelTime: Long,               // 自动取消时间（时间戳）
    val orderStatusName: String,              // 订单状态名称
)
