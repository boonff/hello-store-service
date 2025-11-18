package com.hello.hello_store_service.pay

import org.springframework.stereotype.Service

@Service
class PayFactory(
    private val mockPayService: MockPayService
) {
    fun build(): PayService {
        return mockPayService
    }
}