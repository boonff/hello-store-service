package com.hello.hello_store_service.pay

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

@Service
class WcPayService: PayService {
    override fun createOrder(
        orderNo: String,
        amount: Int,
        openid: String,
        description: String
    ): Map<String, String> {
        TODO("Not yet implemented")
    }

    override fun handleNotify(request: HttpServletRequest): String {
        TODO("Not yet implemented")
    }

    override fun queryOrder(orderNo: String): String {
        TODO("Not yet implemented")
    }

    override fun closeOrder(orderNo: String) {
        TODO("Not yet implemented")
    }
}