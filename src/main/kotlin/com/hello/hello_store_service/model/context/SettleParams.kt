package com.hello.hello_store_service.model.context

import com.hello.hello_store_service.model.business.settle.CouponStoreBO
import com.hello.hello_store_service.model.business.settle.SettleDetailBO
import com.hello.hello_store_service.model.business.settle.SkuQuantityBO
import com.hello.hello_store_service.model.business.settle.StoreSettleBO
import com.hello.hello_store_service.model.entity.address.AddressEntity
import com.hello.hello_store_service.model.entity.goods.SpecEntity
import com.hello.hello_store_service.model.entity.goods.SpuEntity
import com.hello.hello_store_service.model.entity.store.StoreEntity

data class SettleParams(
    val settleDetailBO: SettleDetailBO,
    val userAddress: AddressEntity?,
    val storeToSkus: Map<StoreEntity, List<SkuQuantityBO>>,
    val storeSettleBO: StoreSettleBO,
    val couponList: List<CouponStoreBO>?,
    val spuMap: Map<String, SpuEntity>,
    val specMap: Map<String, SpecEntity>
)