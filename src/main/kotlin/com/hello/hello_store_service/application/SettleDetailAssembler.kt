package com.hello.hello_store_service.application

import com.hello.hello_store_service.model.business.settle.SettleDetailBO
import com.hello.hello_store_service.model.business.settle.SkuQuantityBO
import com.hello.hello_store_service.model.business.settle.StoreSettleBO
import com.hello.hello_store_service.model.business.settle.toCouponStoreBO
import com.hello.hello_store_service.model.business.settle.toSkuQuantityBO
import com.hello.hello_store_service.model.context.SettleParams
import com.hello.hello_store_service.model.entity.address.AddressEntity
import com.hello.hello_store_service.model.entity.goods.SkuEntity
import com.hello.hello_store_service.model.entity.goods.SpecEntity
import com.hello.hello_store_service.model.entity.goods.SpuEntity
import com.hello.hello_store_service.model.entity.store.StoreEntity
import com.hello.hello_store_service.model.transfer.payment.SettleDetailRequest
import com.hello.hello_store_service.service.activity.CouponService
import com.hello.hello_store_service.service.address.AddressService
import com.hello.hello_store_service.service.goods.SkuService
import com.hello.hello_store_service.service.goods.SpecService
import com.hello.hello_store_service.service.goods.SpuService
import com.hello.hello_store_service.service.store.StoreService
import org.springframework.stereotype.Service

@Service
class SettleDetailAssembler(
    private val storeService: StoreService,
    private val spuService: SpuService,
    private val skuService: SkuService,
    private val couponService: CouponService,
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

    private fun settleDetailBO(request: SettleDetailRequest): SettleDetailBO =
        SettleDetailBO(
            skuList = skuQuantityBOs(request),
            couponList = couponStoreBOs(request)
        )

    private fun storeSettleBO(request: SettleDetailRequest): StoreSettleBO =
        StoreSettleBO(
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