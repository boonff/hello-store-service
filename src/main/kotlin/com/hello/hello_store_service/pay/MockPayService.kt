package com.hello.hello_store_service.pay

import org.springframework.stereotype.Service


@Service
class MockPayService : PayService {
    override fun pay(orderId: String, amount: Double): Boolean {
        return randomOutcome()
    }

    override fun getPaymentStatus(orderId: String): Boolean {
        return randomOutcome()
    }

    override fun refund(orderId: String, amount: Double): Boolean {
        return randomOutcome()
    }

    private fun randomOutcome(): Boolean {
        return Math.random() > 0
    }
}