package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.entity.order.OrderEntity
import com.hello.hello_store_service.data.entity.order.OrderStatus
import com.hello.hello_store_service.data.entity.order.ServiceReceiptStatus
import com.hello.hello_store_service.data.entity.order.ServiceStatus
import com.hello.hello_store_service.data.entity.order.ServiceType
import com.hello.hello_store_service.data.repository.OrderRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

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

    fun deleteById(orderId: String): Boolean {
        return orderRepository.deleteById(orderId).equals(false)
    }

    // 更新订单状态
    fun updateStatus(orderId: String, newStatus: OrderStatus): OrderEntity? {
        val order = fetchById(orderId)
        return order?.let {
            val updatedOrder = it.copy(
                status = newStatus,
                updateTime = LocalDateTime.now()
            )
            orderRepository.save(updatedOrder)
        }
    }

    // 更新取消类型
    fun updateCancelType(orderId: String, newCancelType: ServiceType?): OrderEntity? {
        val order = fetchById(orderId)
        return order?.let {
            val updatedOrder = it.copy(
                cancelType = newCancelType,
                updateTime = LocalDateTime.now()
            )
            orderRepository.save(updatedOrder)
        }
    }

    // 更新取消原因类型
    fun updateCancelReasonType(orderId: String, newCancelReasonType: ServiceReceiptStatus?): OrderEntity? {
        val order = fetchById(orderId)
        return order?.let {
            val updatedOrder = it.copy(
                cancelReasonType = newCancelReasonType,
                updateTime = LocalDateTime.now()
            )
            orderRepository.save(updatedOrder)
        }
    }

    // 更新取消原因
    fun updateCancelReason(orderId: String, newCancelReason: String?): OrderEntity? {
        val order = fetchById(orderId)
        return order?.let {
            val updatedOrder = it.copy(
                cancelReason = newCancelReason,
                updateTime = LocalDateTime.now()
            )
            orderRepository.save(updatedOrder)
        }
    }

    // 更新权益类型
    fun updateRightsType(orderId: String, newRightsType: ServiceStatus?): OrderEntity? {
        val order = fetchById(orderId)
        return order?.let {
            val updatedOrder = it.copy(
                rightsType = newRightsType,
                updateTime = LocalDateTime.now()
            )
            orderRepository.save(updatedOrder)
        }
    }
}
