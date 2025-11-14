package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.application.order.OrderService
import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.clean.SettleOrderViewClean
import com.hello.hello_store_service.web.request.OrderRequest
import com.hello.hello_store_service.web.view.SettleOrderView
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("order")
class OrderController(
    private val getSettleOrderViewClean: SettleOrderViewClean,
    private val orderService: OrderService
) {
    @PostMapping("/detail")
    fun genSettleDetail(
        @RequestBody request: OrderRequest
    ): SettleOrderView {
        val uid = SecurityUtils.fetchUid()
        val orderEntity = orderService.createOrder(uid, request)

        return if (orderEntity == null) throw IllegalArgumentException("订单创建失败")
        else getSettleOrderViewClean.getOrderDetailView(orderEntity)
    }
}
