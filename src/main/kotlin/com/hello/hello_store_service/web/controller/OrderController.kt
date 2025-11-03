package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.data.service.SettleService
import com.hello.hello_store_service.web.trans.param.SettleDetailRequest
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
