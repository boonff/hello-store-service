package com.hello.hello_store_service.model.converter.settle

import com.hello.hello_store_service.model.business.goods.SpecDetailsBO
import com.hello.hello_store_service.model.business.settle.CouponStoreBO
import com.hello.hello_store_service.model.business.settle.SkuQuantityBO
import com.hello.hello_store_service.model.business.settle.SkuSettleBO
import com.hello.hello_store_service.model.context.SettleParams
import com.hello.hello_store_service.model.entity.goods.SpecEntity
import com.hello.hello_store_service.model.entity.goods.SpuEntity
import com.hello.hello_store_service.model.view.settle.SkuSettleDetail
import com.hello.hello_store_service.model.view.settle.StoreSettleDetail

object StoreSettlesConv {
    fun convert(settleParams: SettleParams): List<StoreSettleDetail> =
        settleParams.storeToSkus.map { (store, skus) ->
            StoreSettleDetail.from(
                bo = settleParams.storeSettleBO,
                store = store,
                skuDetails = fetchSkuDetails(
                    skus,
                    settleParams.couponList,
                    settleParams.spuMap,
                    settleParams.specMap
                )
            )
        }

    private fun fetchSkuDetails(
        skus: List<SkuQuantityBO>,
        couponList: List<CouponStoreBO>?,
        spuMap: Map<String, SpuEntity>,
        specMap: Map<String, SpecEntity>
    ): List<SkuSettleDetail> =
        skus.map { skuQuantityBO ->
            SkuSettleDetail.from(
                skuBO = SkuSettleBO(
                    skuQuantityBO = skuQuantityBO,
                    couponList = couponList,
                    spuMap = spuMap
                ),
                specBO = SpecDetailsBO(
                    specMap = specMap,
                    sku = skuQuantityBO.skuEntity
                )
            )
        }
}
