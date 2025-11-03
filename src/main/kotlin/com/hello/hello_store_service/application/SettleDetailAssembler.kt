package com.hello.hello_store_service.application

import com.hello.hello_store_service.web.trans.business.settle.OrderSettlement
import com.hello.hello_store_service.web.trans.business.settle.SkuQuantityBO
import com.hello.hello_store_service.web.trans.business.settle.StoreSettlement
import com.hello.hello_store_service.web.trans.business.settle.toCouponStoreBO
import com.hello.hello_store_service.web.trans.business.settle.toSkuQuantityBO
import com.hello.hello_store_service.web.trans.context.SettleParams
import com.hello.hello_store_service.data.model.entity.address.AddressEntity
import com.hello.hello_store_service.data.model.entity.goods.SkuEntity
import com.hello.hello_store_service.data.model.entity.goods.SpecEntity
import com.hello.hello_store_service.data.model.entity.goods.SpuEntity
import com.hello.hello_store_service.data.model.entity.StoreEntity
import com.hello.hello_store_service.web.trans.param.SettleDetailRequest
import com.hello.hello_store_service.data.service.coupon.UserCouponService
import com.hello.hello_store_service.data.service.AddressService
import com.hello.hello_store_service.data.service.goods.SkuService
import com.hello.hello_store_service.data.service.goods.SpecService
import com.hello.hello_store_service.data.service.goods.SpuService
import com.hello.hello_store_service.data.service.StoreService
import org.springframework.stereotype.Service

@Service
class SettleDetailAssembler(
    private val storeService: StoreService,
    private val spuService: SpuService,
    private val skuService: SkuService,
    private val couponService: UserCouponService,
    private val addressService: AddressService,
    private val specService: SpecService
) {
    fun build(request: SettleDetailRequest): SettleParams =
        SettleParams(
            settleDetailBO = settleDetailBO(request),
            userAddress = userAddress(request.userAddressId),
            storeToSkus = storeToSkus(request),
            storeSettleBO = storeSettleBO(request),
            couponList = couponStoreBOs(request),
            spuMap = spuMap(),
            specMap = specMap()
        )

    private fun storeToSkus(
        request: SettleDetailRequest
    ): Map<StoreEntity, List<SkuQuantityBO>> {
        val skuEntities = request.skuList.mapNotNull { (skuId, quantity) ->
            skuService.fetchById(skuId)?.toSkuQuantityBO(quantity)
        }
        return skuEntities.mapNotNull { sku ->
            val store = storeService.getStoreById(sku.skuEntity.storeId)
            store?.let { store to sku }
        }.groupBy({ it.first }, { it.second })
    }

    private fun spuMap(): Map<String, SpuEntity> =
        spuService.fetchAll().associateBy { it.spuId }

    private fun specMap(): Map<String, SpecEntity> =
        specService.getAll().associateBy { it.specId }

    private fun settleDetailBO(request: SettleDetailRequest): OrderSettlement =
        OrderSettlement(
            skuList = skuQuantityBOs(request),
            couponList = couponStoreBOs(request)
        )

    private fun storeSettleBO(request: SettleDetailRequest): StoreSettlement =
        StoreSettlement(
            spuMap = spuService.fetchAll().associateBy { it.spuId },
            skuList = skuQuantityBOs(request),
            couponList = couponStoreBOs(request)
        )

    private fun skuStoreId(sku: SkuEntity): String? =
        spuService.fetchBySpuId(sku.spuId)?.storeId

    private fun skuQuantityBOs(request: SettleDetailRequest) =
        request.skuList.mapNotNull {
            skuService.fetchById(it.skuId)
                ?.toSkuQuantityBO(it.quantity)

        }

    private fun couponStoreBOs(request: SettleDetailRequest) =
        request.couponIdList?.mapNotNull {
            couponService.fetchCouponById(it.couponId)
                ?.toCouponStoreBO(it.storeId)

        }

    private fun userAddress(id: String): AddressEntity? =
        addressService.fetchAddressById(id)
}