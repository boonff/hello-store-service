package com.hello.hello_store_service.web.trans.business.settle

import com.hello.hello_store_service.data.model.entity.goods.SpuEntity

class StoreSettlement(
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
            couponItem.storeId?.let { id ->
                id == storeId
            } ?: true //TODO 如果优惠券没有设置storeId，默认所有商店可用
        }

    private fun getSettleDetailBO(
        skuList: List<SkuQuantityBO>,
        couponList: List<CouponStoreBO>?
    ): OrderSettlement = OrderSettlement(skuList, couponList)

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