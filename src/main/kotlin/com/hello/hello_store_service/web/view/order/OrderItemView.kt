package com.hello.hello_store_service.web.view.order

import com.hello.hello_store_service.web.view.SpecInfo

data class OrderItemView(
    val id: String,                      // 订单号
    val orderNo: String?,                // 订单号
    val spuId: String,                   // 商品SPU ID
    val skuId: String,                   // 商品SKU ID
    val goodsName: String,               // 商品名称
    val goodsPictureUrl: String?,        // 商品图片URL
    val originPrice: Int,                // 商品原价（分为单位）
    val actualPrice: Int,                // 商品实际价格（分为单位）
    val specifications: List<SpecInfo>, // 商品规格列表（颜色、尺寸等）
    val buyQuantity: Int,                // 购买数量
    val itemTotalAmount: Int,            // 商品总金额（分为单位）
    val itemDiscountAmount: Int,         // 商品折扣金额（分为单位）
    val itemPaymentAmount: Int,          // 商品应付金额（分为单位）
    val goodsPaymentPrice: Int,          // 商品支付价格（分为单位）
    val tagPrice: Int?,                  // 标签价格
    val tagText: String?,                // 标签文本
    val outCode: String?,                // 外部编码
    val labelVOs: List<Any>?,            // 商品标签列表
    val buttonVOs: List<ButtonView>?     // 商品操作按钮列表
)

