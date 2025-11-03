package com.hello.hello_store_service.web.trans.business.settle

import com.hello.hello_store_service.data.model.entity.CouponType
import com.hello.hello_store_service.data.model.entity.goods.SpuEntity

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
    fun discountFee(): Int {
        couponList?.forEach { (coupon, storeId) ->
            if (coupon.type == CouponType.PriceOff) {
                coupon.discountRate?.let { discountRate ->
                    val discountAmount = oriFee() * discountRate
                    return discountAmount.toInt()
                }?:0
            }
        }
        return oriFee()
    }

    fun realFee(): Int = discountFee()
    fun settleFee(): Int = discountFee()
    fun reminderStock() = skuEntity.stockInfo.stockQuantity

 
}