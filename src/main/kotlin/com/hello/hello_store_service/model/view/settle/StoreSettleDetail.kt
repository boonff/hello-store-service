package com.hello.hello_store_service.model.view.settle

import com.hello.hello_store_service.model.business.settle.StoreSettleBO
import com.hello.hello_store_service.model.entity.store.StoreEntity
import com.hello.hello_store_service.model.transfer.payment.CouponRef

data class StoreSettleDetail(
    val storeId: String,                // 店铺 ID
    val storeName: String,              // 店铺名称
    val remark: String? = null,                    // 备注（可为标志位或备注数量）
    val goodsCount: Int,                // 商品数量
    val deliveryFee: Int,               // 运费
    val deliveryWords: String,          // 运费说明文字
    val storeTotalAmount: Int,          // 店铺商品总金额
    val storeTotalPayAmount: Int,       // 店铺实际支付金额
    val storeTotalDiscountAmount: Int,  // 店铺优惠总额
    val storeTotalCouponAmount: Int,    // 店铺优惠券优惠金额
    val skuDetailVos: List<SkuSettleDetail>?,        // 商品明细信息
    val couponList: List<CouponRef>?   // 可用优惠券列表
) {
    companion object {
        fun from(
            bo: StoreSettleBO,
            store: StoreEntity,
            skuDetails: List<SkuSettleDetail>,
        ): StoreSettleDetail = StoreSettleDetail(
            storeId = store.storeId,
            storeName = store.storeName,
            remark = null,                  // TODO 不清楚这个字段的含义
            goodsCount = bo.goodsCount(store.storeId),
            deliveryFee = bo.deliveryFee(store.storeId),
            deliveryWords = bo.deliveryWords(store.storeId),
            storeTotalAmount = bo.totalFee(store.storeId),
            storeTotalPayAmount = bo.payFee(store.storeId),
            storeTotalDiscountAmount = bo.discountFee(store.storeId),
            storeTotalCouponAmount = bo.couponFee(store.storeId),
            skuDetailVos = skuDetails,
            couponList = null               // TODO 推测该字段可以删除
        )

    }

}