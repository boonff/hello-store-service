package com.hello.hello_store_service.model.business.settle

import com.hello.hello_store_service.model.entity.goods.SpuEntity
import java.math.BigDecimal

class StoreSettleBO(
    private val spuMap: Map<String, SpuEntity>,
    private val skuList: List<SkuQuantityBO>,
    private val couponList: List<CouponStoreBO>?
) {
    private fun skusByStoreId(storeId: String): List<SkuQuantityBO> =
        skuList.filter { skuItem ->
            spuMap[skuItem.skuEntity.skuId]?.storeId == storeId
        }

    private fun couponsByStoreId(storeId: String): List<CouponStoreBO>? =
        couponList?.filter { couponItem ->
            couponItem.storeId == storeId
        }

    private fun getSettleDetailBO(
        skuList: List<SkuQuantityBO>,
        couponList: List<CouponStoreBO>?
    ): SettleDetailBO = SettleDetailBO(skuList, couponList)

    fun goodsCount(storeId: String): Int =
        skusByStoreId(storeId).size

    //TODO 计算运费
    fun deliveryFee(storeId: String): Int = 0

    //TODO 运费说明
    fun deliveryWords(storeId: String): String = "运费说明"

    fun totalFee(storeId: String): Int =
        getSettleDetailBO(
            skusByStoreId(storeId),
            couponsByStoreId(storeId)
        ).totalFee()


    fun payFee(storeId: String): Int =
        getSettleDetailBO(
            skusByStoreId(storeId),
            couponsByStoreId(storeId)
        ).payFee()

    fun couponFee(storeId: String): Int =
        getSettleDetailBO(
            skusByStoreId(storeId),
            couponsByStoreId(storeId)
        ).couponFee()

    fun discountFee(storeId: String): Int =
        getSettleDetailBO(
            skusByStoreId(storeId),
            couponsByStoreId(storeId)
        ).discountFee()



}