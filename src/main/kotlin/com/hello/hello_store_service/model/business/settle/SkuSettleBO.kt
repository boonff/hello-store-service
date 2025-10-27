package com.hello.hello_store_service.model.business.settle

import com.hello.hello_store_service.model.entity.activity.CouponType
import com.hello.hello_store_service.model.entity.goods.SpuEntity
import java.math.BigDecimal

class SkuSettleBO(
    private val skuQuantityBO: SkuQuantityBO,
    private val couponList: List<CouponStoreBO>?,
    private val spuMap: Map<String, SpuEntity>
) {
    val skuEntity get() = skuQuantityBO.skuEntity
    
    fun spuId() = skuEntity.spuId
    fun goodsName() = spuMap[spuId()]?.title ?: "null"
    fun egoodsName() = spuMap[spuId()]?.etitle ?: "null"
    fun promotionIds() = null  //TODO 获取活动列表
    fun quantity() = skuQuantityBO.quantity
    fun oriFee() = skuEntity.salePrice
    fun payFee() = skuEntity.salePrice
    fun discountFee(): BigDecimal {
        couponList?.forEach { (coupon, storeId) ->
            if (coupon.type == CouponType.PriceOff)
                return oriFee().multiply(coupon.discountRate?.toBigDecimal())
        }
        return oriFee()
    }

    fun realFee(): BigDecimal = discountFee()
    fun settleFee(): BigDecimal = discountFee()
    fun reminderStock() = skuEntity.stockInfo.stockQuantity

 
}