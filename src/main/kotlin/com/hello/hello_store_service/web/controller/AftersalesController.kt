package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.application.AftersalesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/aftersales")
class AftersalesController(
    private val aftersalesService: AftersalesService
) {
    @GetMapping("refund")
    fun canFastRefund(
        @RequestParam("orderId") orderId: String
    ): Boolean {
        return aftersalesService.canFastRefund(orderId)
    }

    @GetMapping("return")
    fun canReturn(
        @RequestParam("orderId") orderId: String
    ): Boolean {
        return aftersalesService.canReturn(orderId)
    }
}