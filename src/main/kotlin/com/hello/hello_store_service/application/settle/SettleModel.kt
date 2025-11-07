package com.hello.hello_store_service.application.settle

data class SettleModel (
    val totalGoodsCount: Int,           // 商品总数量
    val totalFee: Int,               // 商品原始总金额
    val totalPayFee: Int,            // 实际需支付金额
    val totalDiscountFee: Int,       // 优惠总金额
    val totalPromotionFee: Int,      // 活动优惠总金额
    val totalCouponFee: Int,         // 优惠券优惠总金额
    val totalSaleFee: Int,            // 销售价总额（打折后价格，不包括运费）
    val totalGoodsFee: Int,          // 商品结算金额总计
    val totalDeliveryFee: Int,          // 运费总金额
)