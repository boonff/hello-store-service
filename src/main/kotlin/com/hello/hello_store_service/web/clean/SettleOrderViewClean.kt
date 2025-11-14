package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.goods.SkuService
import com.hello.hello_store_service.application.goods.SpecDetail
import com.hello.hello_store_service.application.order.OrderRule
import com.hello.hello_store_service.data.entity.OrderEntity
import com.hello.hello_store_service.data.entity.address.AddressEntity
import com.hello.hello_store_service.data.service.AddressDataService
import com.hello.hello_store_service.data.service.goods.SpuDataService
import com.hello.hello_store_service.web.request.OrderRequest
import com.hello.hello_store_service.web.view.SettleOrderView
import com.hello.hello_store_service.web.view.SkuOrderView
import com.hello.hello_store_service.web.view.goods.SpecDetailView
import org.springframework.stereotype.Service

@Service
class SettleOrderViewClean(

    private val addressDataService: AddressDataService,
    private val spuDataService
    : SpuDataService,
    private val calculator: OrderRule,
    private val skuService: SkuService

) {
    fun getOrderDetailView(orderEntity: OrderEntity): SettleOrderView {
        val addressEntity = fetchAddress(orderEntity.addressId)

        return SettleOrderView(
            settleType = orderType(addressEntity),
            userAddress = addressEntity,
            totalGoodsCount = orderEntity.totalFee,
            packageCount = orderEntity.deliveryFee,
            totalAmount = orderEntity.totalFee,
            totalPayAmount = orderEntity.paymentFee,
            totalDiscountAmount = orderEntity.discountFee,
            totalPromotionAmount = 0,
            totalCouponAmount = orderEntity.couponFee,
            totalSalePrice = orderEntity.saleFee,
            totalGoodsAmount = orderEntity.totalFee,
            totalDeliveryFee = orderEntity.deliveryFee,
            invoiceRequest = false,
            skuImages = null,
            deliveryFeeList = null,
            goodsList = getSkuOrderViews(orderEntity)
        )
    }


    private fun getSkuOrderViews(orderEntity: OrderEntity): List<SkuOrderView> {
        return orderEntity.orderItems.mapNotNull { item ->
            val skuModel = skuService.fetchSkuModel(item.skuId) ?: return@mapNotNull null
            val spuModel = spuDataService.fetchById(skuModel.spuId) ?: return@mapNotNull null
            SkuOrderView(
                skuId = item.skuId,
                roomId = null,
                egoodsName = spuModel.etitle,
                goodsName = spuModel.title,
                image = item.skuImage,
                masterGoodsType = 0,
                promotionIds = null,
                quantity = item.quantity,
                oriPrice = 0,
                payPrice = 0,
                discountSettlePrice = 0,
                realSettlePrice = 0,
                reminderStock = skuModel.stockInfo.stockQuantity,
                settlePrice = item.saleFee,
                skuSpecLst = getSpecDetailView(skuModel.specList)
            )
        }
    }

    private fun getSpecDetailView(specDetails: List<SpecDetail>): List<SpecDetailView> {
        return specDetails.map { specDetail ->
            SpecDetailView.from(specDetail)
        }
    }


    private fun fetchAddress(addressId: String?): AddressEntity? {
        if (addressId == null) return null
        return addressDataService.fetchAddressById(addressId)
    }

    private fun orderType(userAddress: AddressEntity?): Int =
        userAddress?.let { 1 } ?: 0
}