package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.pay.WcPayService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pay")
class PayController(
    private val wcPayService: WcPayService
) {
//    @PostMapping("wechat")
//    fun wcPay(@RequestBody orderRequest: OrderRequest): Map<String, String> {
//        return wcPayService.createOrder()
//    }
}