package com.hello.hello_store_service.application.order

import com.hello.hello_store_service.pay.PayService
import org.springframework.stereotype.Service

@Service
class OrderPayService(
    private val orderRule: OrderRule,
    private val payService: PayService
) {
//    fun pay(param: OrderParam): Boolean{
//
//    }

    private fun payFee(param: OrderParam): Int {
        return orderRule.payFee(param)
    }
}