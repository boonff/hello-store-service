package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.clean.SettleOrderViewClean
import com.hello.hello_store_service.web.model.request.OrderRequest
import com.hello.hello_store_service.web.model.view.SettleOrderView
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("order")
class OrderController(
    private val getSettleOrderViewClean: SettleOrderViewClean
) {
    @PostMapping("/detail")
    fun genSettleDetail(
        @RequestBody request: OrderRequest
    ): SettleOrderView {
        val username = SecurityUtils.currentUsername()
        return getSettleOrderViewClean.getOrderDetailView(request, username)
    }
}
