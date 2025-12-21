package com.hello.hello_store_service.application

import com.hello.hello_store_service.data.entity.order.OrderStatus
import com.hello.hello_store_service.data.service.OrderDataService
import com.hello.hello_store_service.pay.PayFactory
import com.hello.hello_store_service.utils.AmountUtil
import org.springframework.stereotype.Service

@Service
class TradeService(
    private val payFactory: PayFactory,
    private val orderDataService: OrderDataService
) {
    fun payOrder(orderId: String): Boolean {
        val status = mockPay(orderId)
        return if (status) {
            orderDataService.updateStatus(orderId, OrderStatus.PENDING_DELIVERY) != null
        } else false
    }

    private fun mockPay(orderId: String): Boolean {
        val payService = payFactory.build()
        val orderEntity = orderDataService.fetchById(orderId) ?: return false
        return payService.pay(
            orderId,
            AmountUtil.fenToYuan(orderEntity.paymentFee)
        )
    }
}