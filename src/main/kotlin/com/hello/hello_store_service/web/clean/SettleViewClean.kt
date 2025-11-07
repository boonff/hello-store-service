package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.goods.SkuService
import com.hello.hello_store_service.application.goods.SpecDetail
import com.hello.hello_store_service.application.settle.SettleCalculator
import com.hello.hello_store_service.application.settle.SettleParam
import com.hello.hello_store_service.data.model.entity.StoreEntity
import com.hello.hello_store_service.data.model.entity.address.AddressEntity
import com.hello.hello_store_service.data.service.AddressDataService
import com.hello.hello_store_service.data.service.goods.SpuDataService
import com.hello.hello_store_service.web.model.request.SettleDetailRequest
import com.hello.hello_store_service.web.model.view.SettleView
import com.hello.hello_store_service.web.model.view.SkuSettleView
import com.hello.hello_store_service.web.model.view.StoreSettleView
import com.hello.hello_store_service.web.model.view.goods.SpecDetailView
import org.springframework.stereotype.Service

@Service
class SettleViewClean(

    private val addressDataService: AddressDataService,
    private val spuDataService: SpuDataService,
    private val calculator: SettleCalculator,
    private val skuService: SkuService

) {
    fun getSettleDetailView(request: SettleDetailRequest, username: String): SettleView {
        val param = getSettleParam(request, username)
        val addressEntity = fetchAddress(request.userAddressId)

        return SettleView(
            settleType = settleType(addressEntity),
            userAddress = addressEntity,
            totalGoodsCount = calculator.goodsCount(param),
            packageCount = calculator.packageCount(param),
            totalAmount = calculator.totalFee(param),
            totalPayAmount = calculator.payFee(param),
            totalDiscountAmount = calculator.discountFee(param),
            totalPromotionAmount = 0,
            totalCouponAmount = calculator.couponFee(param),
            totalSalePrice = calculator.saleFee(param),
            totalGoodsAmount = calculator.totalFee(param),
            totalDeliveryFee = calculator.deliveryFee(),
            invoiceRequest = false,
            skuImages = null,
            deliveryFeeList = null,
            storeGoodsList = getStoreViews(param)
        )
    }

    private fun getStoreViews(settleParam: SettleParam): List<StoreSettleView> {
        val paramMap = getStoreParams(settleParam)
        return paramMap.map { (store, param) ->
            StoreSettleView(
                storeId = store.storeId,
                storeName = store.storeName,
                remark = null,
                goodsCount = calculator.goodsCount(param),
                deliveryFee = calculator.deliveryFee(),
                deliveryWords = "运费说明", //TODO 运费说明
                storeTotalAmount = calculator.totalFee(param),
                storeTotalPayAmount = calculator.payFee(param),
                storeTotalDiscountAmount = calculator.discountFee(param),
                storeTotalCouponAmount = calculator.couponFee(param),
                couponList = null,
                skuDetailVos = getSkuSettleViews(param)
            )
        }

    }

    private fun getSkuSettleViews(settleParam: SettleParam): List<SkuSettleView> {
        return settleParam.skuList.mapNotNull { skuData ->
            val skuModel = skuService.fetchSkuModel(skuData.skuId) ?: return@mapNotNull null
            val spuEntity = spuDataService.fetchBySpuId(skuModel.spuId) ?: return@mapNotNull null

            SkuSettleView(
                skuId = skuModel.skuId,
                roomId = null,
                egoodsName = spuEntity.etitle,
                goodsName = spuEntity.title,
                image = spuEntity.primaryImage,
                masterGoodsType = 0,
                promotionIds = null,
                quantity = skuData.quantity,
                oriPrice = skuModel.salePrice,
                payPrice = skuModel.salePrice,
                discountSettlePrice = 0,
                realSettlePrice = 0,
                reminderStock = skuModel.stockInfo.stockQuantity,
                settlePrice = 0,
                skuSpecLst = getSpecDetailView(skuModel.specList)
            )
        }
    }

    private fun getSpecDetailView(specDetails: List<SpecDetail>): List<SpecDetailView> {
        return specDetails.map { specDetail ->
            SpecDetailView.from(specDetail)
        }
    }

    private fun getSettleParam(request: SettleDetailRequest, username: String): SettleParam {
        return SettleParam(
            username = username,
            skuList = request.skuList,
            couponIdList = request.couponIdList
        )
    }

    private fun getStoreParams(settleParam: SettleParam): Map<StoreEntity, SettleParam> {
        val skuGroup = settleParam.skuList
            .mapNotNull { skuData ->
                skuService.fetchStore(skuData.skuId)?.let { storeId ->
                    storeId to skuData
                }
            }.groupBy({ it.first }, { it.second })

        return skuGroup.mapValues { (storeId, skuIds) ->
            SettleParam(
                username = settleParam.username,
                skuList = skuIds,
                couponIdList = settleParam.couponIdList
            )
        }
    }

    private fun fetchAddress(addressId: String?): AddressEntity? {
        if (addressId == null) return null
        return addressDataService.fetchAddressById(addressId)
    }

    private fun settleType(userAddress: AddressEntity?): Int =
        userAddress?.let { 1 } ?: 0

}