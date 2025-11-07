package com.hello.hello_store_service.web.model.view.order

data class OrderView(
    val saasId: String,                       // SaaS 商户ID
    val storeId: String,                      // 店铺ID
    val storeName: String,                    // 店铺名称
    val uid: String,                          // 用户ID
    val parentOrderNo: String,                // 父订单号（若存在子订单）
    val orderId: String,                      // 订单ID
    val orderNo: String,                      // 订单号
    val orderType: Int,                       // 订单类型
    val orderSubType: Int,                    // 订单子类型
    val orderStatus: Int,                     // 订单状态
    val orderSubStatus: String?,              // 订单子状态
    val totalAmount: String,                  // 订单总金额（分）
    val goodsAmount: String,                  // 商品金额（分）
    val goodsAmountApp: String,               // 商品金额（应用计算）（分）
    val paymentAmount: String,                // 实付金额（分）
    val freightFee: String,                   // 运费（分）
    val packageFee: String,                   // 包装费（分）
    val discountAmount: String,               // 折扣金额（分）
    val channelType: Int,                     // 渠道类型
    val channelSource: String,                // 渠道来源
    val channelIdentity: String,              // 渠道标识
    val remark: String,                       // 订单备注
    val cancelType: String?,                  // 取消类型
    val cancelReasonType: String?,            // 取消原因类型
    val cancelReason: String?,                // 取消原因
    val rightsType: String?,                  // 维权类型
    val createTime: String,                   // 订单创建时间（时间戳）
    val orderItemVOs: List<OrderItemView>,    // 商品列表
    val logisticsVO: LogisticsView,           // 物流信息
    val paymentVO: PaymentView,               // 支付信息
    val buttonVOs: List<ButtonView>,          // 订单操作按钮列表
    val labelVOs: List<Any>?,                 // 标签列表
    val invoiceVO: Any?,                      // 发票信息
    val couponAmount: String?,                // 优惠券金额（分）
    val autoCancelTime: String,               // 自动取消时间（时间戳）
    val orderStatusName: String,              // 订单状态名称
    val orderStatusRemark: String,            // 订单状态说明
    val logisticsLogVO: Any?,                 // 物流日志信息
    val invoiceStatus: String?,               // 发票状态
    val invoiceDesc: String?,                 // 发票说明
    val invoiceUrl: String?                   // 发票链接
)
