package com.hello.hello_store_service.application.order

import com.hello.hello_store_service.application.goods.SkuModel
import com.hello.hello_store_service.application.goods.SkuModelAssembler
import com.hello.hello_store_service.data.entity.order.OrderEntity
import com.hello.hello_store_service.data.entity.order.OrderItem
import com.hello.hello_store_service.data.entity.order.OrderStatus
import com.hello.hello_store_service.data.service.goods.SkuDataService
import com.hello.hello_store_service.web.request.OrderRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderEntityAssembler(
    private val skuDataService: SkuDataService,
    private val skuModelAssembler: SkuModelAssembler
) {
    fun genOrderEntity(
        orderId: String?,
        uid: String,
        totalFee: Int,
        discountFee: Int,
        couponFee: Int,
        saleFee: Int,
        paymentFee: Int,
        deliveryFee: Int,
        currentTime: LocalDateTime,
        param: OrderRequest
    ): OrderEntity {
        return OrderEntity(
            orderId = orderId,
            uid = uid,
            status = OrderStatus.PENDING_PAYMENT,
            totalFee = totalFee,
            discountFee = discountFee,
            couponFee = couponFee,
            saleFee = saleFee,
            paymentFee = paymentFee,
            deliveryFee = deliveryFee,
            remark = param.remark,
            logisticsId = null,
            addressId = param.userAddressId,
            orderItems = genOrderItems(param),
            autoCancelTime = null,
            createTime = currentTime,
            updateTime = currentTime,
        )
    }

    private fun genOrderItems(param: OrderRequest): List<OrderItem> {
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