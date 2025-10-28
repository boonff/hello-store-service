package com.hello.hello_store_service.model.view.settle

import com.hello.hello_store_service.model.business.settle.SettleDetailBO
import com.hello.hello_store_service.model.entity.address.AddressEntity

data class SettleDetail(
    val settleType: Int,                // 结算类型（如立即购买、购物车结算等）
    val userAddress: AddressEntity?,     // 用户收货地址信息
    val totalGoodsCount: Int,           // 商品总数量
    val packageCount: Int,              // 包裹总数量
    val totalAmount: Int,               // 商品原始总金额
    val totalPayAmount: Int,            // 实际需支付金额
    val totalDiscountAmount: Int,       // 优惠总金额
    val totalPromotionAmount: Int,      // 活动优惠总金额
    val totalCouponAmount: Int,         // 优惠券优惠总金额
    val totalSalePrice: Int,            // 销售价总额（打折后价格，不包括运费）
    val totalGoodsAmount: Int,          // 商品结算金额总计
    val totalDeliveryFee: Int,          // 运费总金额
    val invoiceRequest: Boolean = false,        // 是否需要发票
    val skuImages: List<String>? = null,              // 商品图片（拼接或主图）
    val deliveryFeeList: List<Int>? = null, // 各包裹运费列表
    val storeGoodsList: List<StoreSettleDetail>    // 店铺商品明细列表
) {
    companion object{
        fun from(
            bo: SettleDetailBO,
            storeGoodsSettle: List<StoreSettleDetail>,
            userAddress: AddressEntity?
        ): SettleDetail = SettleDetail(
            settleType = settleType(userAddress),
            userAddress = userAddress,
            totalGoodsCount = bo.goodsCount(),
            packageCount = bo.goodsCount(),//TODO 计算包裹数量
            totalAmount = bo.totalFee(),
            totalPayAmount = bo.payFee(),
            totalDiscountAmount = bo.discountFee(),
            totalPromotionAmount = bo.promotionFee(),
            totalCouponAmount = bo.couponFee(),
            totalSalePrice = bo.saleFee(),
            totalGoodsAmount = bo.totalFee(), //TODO 不清楚这个字段的含义
            totalDeliveryFee = bo.deliveryFee(),
            invoiceRequest = false,         //TODO 发票功能
            skuImages = null,               //TODO 这个字段好像没用
            deliveryFeeList = null,         //TODO 包裹的运费列表
            storeGoodsList = storeGoodsSettle
        )

        private fun settleType(userAddress: AddressEntity?): Int =
            userAddress?.let { 1 } ?: 0
    }

}







