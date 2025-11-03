package com.hello.hello_store_service.web.trans.converter.settle

import com.hello.hello_store_service.data.model.entity.goods.SpecEntity
import com.hello.hello_store_service.data.model.entity.goods.SpuEntity
import com.hello.hello_store_service.web.trans.business.SpecDetailsBO
import com.hello.hello_store_service.web.trans.business.settle.CouponStoreBO
import com.hello.hello_store_service.web.trans.business.settle.SkuQuantityBO
import com.hello.hello_store_service.web.trans.business.settle.SkuSettleBO
import com.hello.hello_store_service.web.trans.context.SettleParams
import com.hello.hello_store_service.web.trans.view.settle.SkuSettleDetail
import com.hello.hello_store_service.web.trans.view.settle.StoreSettleDetail

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
