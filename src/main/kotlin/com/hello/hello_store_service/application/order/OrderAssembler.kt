package com.hello.hello_store_service.application.order

import com.hello.hello_store_service.application.sku.SkuModel
import com.hello.hello_store_service.application.sku.SkuModelAssembler
import com.hello.hello_store_service.data.entity.order.OrderEntity
import com.hello.hello_store_service.data.entity.order.OrderItem
import com.hello.hello_store_service.data.entity.order.OrderStatus
import com.hello.hello_store_service.data.service.goods.SkuDataService
import com.hello.hello_store_service.web.request.SettleOrderRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderAssembler(
    private val skuDataService: SkuDataService,
    private val skuModelAssembler: SkuModelAssembler
) {
    fun genSettleOrderEntity(
        orderId: String?,
        uid: String,
        goodsQuantity: Int,
        totalFee: Int,
        discountFee: Int,
        couponFee: Int,
        saleFee: Int,
        paymentFee: Int,
        deliveryFee: Int,
        currentTime: LocalDateTime,
        request: SettleOrderRequest
    ): OrderEntity {
        return OrderEntity(
            orderId = orderId,
            uid = uid,
            status = OrderStatus.PENDING_PAYMENT,
            goodsQuantity = goodsQuantity,
            totalFee = totalFee,
            discountFee = discountFee,
            couponFee = couponFee,
            saleFee = saleFee,
            paymentFee = paymentFee,
            deliveryFee = deliveryFee,
            remark = request.remark,
            logisticsId = null,
            addressId = request.userAddressId,
            orderItems = genOrderItems(request),
            autoCancelTime = null,
            createTime = currentTime,
            updateTime = currentTime,
        )
    }

    private fun genOrderItems(param: SettleOrderRequest): List<OrderItem> {
        return param.skuList.mapNotNull { (skuId, quantity) ->
            val skuModel = fetchSkuModel(skuId) ?: return@mapNotNull null
            OrderItem(
                skuId = skuModel.skuId,
                spuId = skuModel.spuId,
                skuImage = skuModel.skuImage,
                saleFee = skuModel.salePrice,
                quantity = quantity
            )
        }
    }

    private fun fetchSkuModel(skuId: String): SkuModel? {
        val skuEntity = skuDataService.fetchById(skuId) ?: return null
        return skuModelAssembler.genSkuModel(skuEntity)
    }
}