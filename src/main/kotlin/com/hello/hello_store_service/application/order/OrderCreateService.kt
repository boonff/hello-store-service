package com.hello.hello_store_service.application.order

import com.hello.hello_store_service.data.entity.order.OrderEntity
import com.hello.hello_store_service.data.entity.order.OrderStatus
import com.hello.hello_store_service.data.service.LogisticDataService
import com.hello.hello_store_service.data.service.OrderDataService
import com.hello.hello_store_service.pay.PayFactory
import com.hello.hello_store_service.utils.PageUtil
import com.hello.hello_store_service.web.request.SettleOrderRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderCreateService(
    private val orderRule: OrderRule,
    private val orderDataService: OrderDataService,
    private val logisticDataService: LogisticDataService,
    private val orderEntityAssembler: OrderAssembler,
    private val payFactory: PayFactory
) {
    fun deleteOrder(orderId: String): Boolean {
        return orderDataService.deleteById(orderId)
    }

    fun fetchById(orderId: String): OrderEntity? {
        return orderDataService.fetchById(orderId)
    }

    fun fetchOrderByRange(
        uid: String,
        pageSize: Int?,
        pageIndex: Int?,
        orderStatus: OrderStatus?
    ): List<OrderEntity> {
        val filter = fetchByUid(uid).filter {
            it.status == orderStatus || orderStatus == null
        }

        return PageUtil.sliceList(filter, pageSize, pageIndex)
    }

    fun fetchByUid(uid: String): List<OrderEntity> {
        return orderDataService.fetchByUid(uid)
    }

    // 和前端结算页面对接的方法。所有订单都在这里被首次创建。
    fun saveSettleOrder(uid: String, request: SettleOrderRequest): OrderEntity? {
        val orderEntity = orderEntityAssembler.genSettleOrderEntity(
            orderId = request.orderId,
            uid = uid,
            goodsQuantity = orderRule.goodsCount(request),
            totalFee = orderRule.totalFee(request),
            discountFee = orderRule.discountFee(uid, request),
            couponFee = orderRule.couponFee(uid, request),
            saleFee = orderRule.saleFee(uid, request),
            paymentFee = orderRule.paymentFee(uid, request),
            deliveryFee = orderRule.deliveryFee(request),
            currentTime = LocalDateTime.now(),
            request = request,
        )
        return orderDataService.save(orderEntity)
    }


}