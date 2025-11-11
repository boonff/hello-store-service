package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.goods.SkuService
import com.hello.hello_store_service.application.goods.SpecDetail
import com.hello.hello_store_service.application.order.OrderRule
import com.hello.hello_store_service.application.order.OrderParam
import com.hello.hello_store_service.data.model.entity.StoreEntity
import com.hello.hello_store_service.data.model.entity.address.AddressEntity
import com.hello.hello_store_service.data.service.AddressDataService
import com.hello.hello_store_service.data.service.goods.SpuDataService
import com.hello.hello_store_service.web.model.request.OrderRequest
import com.hello.hello_store_service.web.model.view.SettleOrderView
import com.hello.hello_store_service.web.model.view.SkuOrderView
import com.hello.hello_store_service.web.model.view.goods.SpecDetailView
import org.springframework.stereotype.Service

@Service
class SettleOrderViewClean(

    private val addressDataService: AddressDataService,
    private val spuDataService
    : SpuDataService,
    private val calculator: OrderRule,
    private val skuService: SkuService

) {
    fun getOrderDetailView(request: OrderRequest, username: String): SettleOrderView {
        val param = getOrderParam(request, username)
        val addressEntity = fetchAddress(request.userAddressId)

        return SettleOrderView(
            settleType = orderType(addressEntity),
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
            goodsList = getSkuOrderViews(param)
        )
    }


    private fun getSkuOrderViews(orderParam: OrderParam): List<SkuOrderView> {
        return orderParam.skuList.mapNotNull { skuData ->
            val skuModel = skuService.fetchSkuModel(skuData.skuId) ?: return@mapNotNull null
            val spuEntity = spuDataService.fetchBySpuId(skuModel.spuId) ?: return@mapNotNull null

            SkuOrderView(
                skuId = skuModel.skuId,
                roomId = null,
                egoodsName = spuEntity.etitle,
                goodsName = spuEntity.title,
                image = spuEntity.primaryImage,
                masterGoodsType = 0,
                promotionIds = null,
                quantity = skuData.quantity,
                oriPrice = 0,
                payPrice = 0,
                discountSettlePrice = 0,
                realSettlePrice = 0,
                reminderStock = skuModel.stockInfo.stockQuantity,
                settlePrice = skuModel.salePrice,
                skuSpecLst = getSpecDetailView(skuModel.specList)
            )
        }
    }

    private fun getSpecDetailView(specDetails: List<SpecDetail>): List<SpecDetailView> {
        return specDetails.map { specDetail ->
            SpecDetailView.from(specDetail)
        }
    }

    private fun getOrderParam(request: OrderRequest, username: String): OrderParam {
        return OrderParam(
            username = username,
            skuList = request.skuList,
            couponIdList = request.couponIdList
        )
    }


    private fun fetchAddress(addressId: String?): AddressEntity? {
        if (addressId == null) return null
        return addressDataService.fetchAddressById(addressId)
    }

    private fun orderType(userAddress: AddressEntity?): Int =
        userAddress?.let { 1 } ?: 0

    private fun getStoreParams(orderParam: OrderParam): Map<StoreEntity, OrderParam> {
        val skuGroup = orderParam.skuList
            .mapNotNull { skuData ->
                skuService.fetchStore(skuData.skuId)?.let { storeId ->
                    storeId to skuData
                }
            }.groupBy({ it.first }, { it.second })

        return skuGroup.mapValues { (_, skuIds) ->
            OrderParam(
                username = orderParam.username,
                skuList = skuIds,
                couponIdList = orderParam.couponIdList
            )
        }
    }
}