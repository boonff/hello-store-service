package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.application.order.OrderService
import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.clean.OrderDetailViewClean
import com.hello.hello_store_service.web.clean.SettleOrderViewClean
import com.hello.hello_store_service.web.request.SettleOrderRequest
import com.hello.hello_store_service.web.request.OrderRequest
import com.hello.hello_store_service.web.request.PayRequest
import com.hello.hello_store_service.web.view.SettleOrderView
import com.hello.hello_store_service.web.view.order.OrderDetailView
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("order")
class OrderController(
    private val settleOrderViewClean: SettleOrderViewClean,
    private val orderDetailViewClean: OrderDetailViewClean,
    private val orderService: OrderService
) {
    @PostMapping("/settle")
    fun fetchSettleDetail(
        @RequestBody request: SettleOrderRequest
    ): SettleOrderView {
        val uid = SecurityUtils.fetchUid()
        val orderEntity = orderService.saveSettleOrder(uid, request)
            ?: throw IllegalArgumentException("订单创建失败")
        return settleOrderViewClean.getOrderDetailView(orderEntity)
            ?: throw IllegalArgumentException("订单创建失败")
    }

    @PostMapping("/list")
    fun fetchOrderList(
        @RequestBody request: OrderRequest
    ): List<OrderDetailView> {
        val uid = SecurityUtils.fetchUid()
        return orderDetailViewClean.fetchOrderDetailViews(uid, request)
    }

    @PostMapping("pay")
    fun payOrder(
        @RequestBody payRequest: PayRequest
    ): Boolean {
        return orderService.payOrder(payRequest.orderId)
    }

    @PostMapping("cancel")
    fun cancelOrder(
        @RequestParam("orderId") orderId: String
    ): Boolean {
        return orderService.deleteOrder(orderId)
    }

    @PostMapping("delete")
    fun deleteOrder(
        @RequestParam("orderId") orderId: String
    ): Boolean {
        return orderService.deleteOrder(orderId)
    }
}
