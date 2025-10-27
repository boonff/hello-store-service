package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.transfer.payment.SettleDetailRequest
import com.hello.hello_store_service.service.SettleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("order")
class OrderController(
    private val settleService: SettleService
) {
    @PostMapping("/detail")
    fun genSettleDetail(
        @RequestBody request: SettleDetailRequest
    ) = settleService.genSettleDetail(request)
}
