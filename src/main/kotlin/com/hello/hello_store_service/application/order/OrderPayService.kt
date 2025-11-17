package com.hello.hello_store_service.application.order

import com.hello.hello_store_service.data.entity.order.OrderEntity
import com.hello.hello_store_service.data.service.OrderDataService
import com.hello.hello_store_service.web.request.OrderRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRule: OrderRule,
    private val orderDataService: OrderDataService,
    private val orderEntityAssembler: OrderEntityAssembler
) {
    fun save(uid: String, request: OrderRequest): OrderEntity? {
        val orderEntity = orderEntityAssembler.genOrderEntity(
            orderId = request.orderId,
            uid = uid,
            totalFee = orderRule.totalFee(request),
            discountFee = orderRule.discountFee(uid, request),
            couponFee = orderRule.couponFee(uid, request),
            saleFee = orderRule.saleFee(uid, request),
            paymentFee = orderRule.paymentFee(uid, request),
            deliveryFee = orderRule.deliveryFee(request),
            currentTime = LocalDateTime.now(),
            param = request,
        )
        return orderDataService.save(orderEntity)
    }

    fun fetchOrder(orderId: String): OrderEntity? {
        return orderDataService.fetchById(orderId)
    }

    fun fetchByUid(uid: String): List<OrderEntity> {
        return orderDataService.fetchByUid(uid)
    }
}