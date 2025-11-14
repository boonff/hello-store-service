package com.hello.hello_store_service.application.order

import com.hello.hello_store_service.data.entity.OrderEntity
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
    fun createOrder(uid: String, param: OrderRequest): OrderEntity? {
        val orderEntity = orderEntityAssembler.genOrderEntity(
            uid = uid,
            totalFee = orderRule.totalFee(param),
            discountFee = orderRule.discountFee(uid, param),
            couponFee = orderRule.couponFee(uid, param),
            saleFee = orderRule.saleFee(uid, param),
            paymentFee = orderRule.paymentFee(uid, param),
            deliveryFee = orderRule.deliveryFee(param),
            currentTime = LocalDateTime.now(),
            param = param,
        )
        return orderDataService.save(orderEntity)
    }

    fun fetchOrder(orderId: String): OrderEntity? {
        return orderDataService.fetchById(orderId)
    }
}