package com.hello.hello_store_service.application

import com.hello.hello_store_service.application.order.OrderCreateService
import com.hello.hello_store_service.data.entity.order.OrderStatus
import com.hello.hello_store_service.utils.TimeUtil
import org.springframework.stereotype.Service

@Service
class AftersalesService(
    private val orderService: OrderCreateService,
    private val logisticService: LogisticService
) {
    // 订单是否可退货
    fun canReturn(orderId: String): Boolean {
        val order = orderService.fetchById(orderId) ?: return false
        val logistic = logisticService.fetchById(orderId)

        return when (order.status) {
            OrderStatus.DELIVERED -> true
            OrderStatus.COMPLETE -> {
                val signTime = logistic?.timeInfo?.signTime ?: return false
                TimeUtil.isWithinNDays(signTime, 7)
            }

            else -> false
        }
    }

    fun canFastRefund(orderId: String): Boolean {
        val orderEntity = orderService.fetchById(orderId) ?: return false
        return orderEntity.status == OrderStatus.PENDING_DELIVERY
    }
}