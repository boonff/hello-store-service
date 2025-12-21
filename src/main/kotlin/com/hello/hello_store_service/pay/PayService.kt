package com.hello.hello_store_service.pay


interface PayService {
    fun pay(orderId: String, amount: Double): Boolean
    fun getPaymentStatus(orderId: String): Boolean
    fun refund(orderId: String, amount: Double): Boolean
}
