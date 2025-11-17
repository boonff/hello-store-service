package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.entity.order.OrderEntity
import com.hello.hello_store_service.data.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderDataService(
    private val orderRepository: OrderRepository
) {
    fun fetchById(orderId: String): OrderEntity? {
        return orderRepository.findById(orderId).orElse(null)
    }

    fun fetchByUid(uid: String): List<OrderEntity> {
        return orderRepository.findByUid(uid)
    }

    fun save(orderEntity: OrderEntity): OrderEntity? {
        return orderRepository.save(orderEntity)
    }
}