package com.hello.hello_store_service.web.trans.context

import com.hello.hello_store_service.data.model.entity.StoreEntity
import com.hello.hello_store_service.data.model.entity.address.AddressEntity
import com.hello.hello_store_service.data.model.entity.goods.SpecEntity
import com.hello.hello_store_service.data.model.entity.goods.SpuEntity
import com.hello.hello_store_service.web.trans.business.settle.CouponStoreBO
import com.hello.hello_store_service.web.trans.business.settle.OrderSettlement
import com.hello.hello_store_service.web.trans.business.settle.SkuQuantityBO
import com.hello.hello_store_service.web.trans.business.settle.StoreSettlement

data class SettleParams(
    val settleDetailBO: OrderSettlement,
    val userAddress: AddressEntity?,
    val storeToSkus: Map<StoreEntity, List<SkuQuantityBO>>,
    val storeSettleBO: StoreSettlement,
    val couponList: List<CouponStoreBO>?,
    val spuMap: Map<String, SpuEntity>,
    val specMap: Map<String, SpecEntity>
)