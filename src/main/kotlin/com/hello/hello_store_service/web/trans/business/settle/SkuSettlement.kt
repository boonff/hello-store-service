package com.hello.hello_store_service.web.trans.business.settle

import com.hello.hello_store_service.data.model.entity.CouponType
import com.hello.hello_store_service.data.model.entity.goods.SpuEntity


class SkuSettlement (
    private val skuQuantityBO: SkuQuantityBO,
    private val couponList: List<CouponStoreBO>?,
    private val spuMap: Map<String, SpuEntity>
) {
    val skuEntity get() = skuQuantityBO.skuEntity


    fun quantity() = skuQuantityBO.quantity
    fun totalFee() = skuEntity.salePrice
    fun payFee() = skuEntity.salePrice
    fun discountFee(): Int {
        couponList?.forEach { (coupon, storeId) ->
            if (coupon.type == CouponType.PriceOff) {
                coupon.discountRate?.let { discountRate ->
                    val discountAmount = totalFee() * discountRate
                    return discountAmount.toInt()
                }?:0
            }
        }
        return totalFee()
    }

    fun realFee(): Int = discountFee()
    fun settleFee(): Int = discountFee()
    fun reminderStock() = skuEntity.stockInfo.stockQuantity


}