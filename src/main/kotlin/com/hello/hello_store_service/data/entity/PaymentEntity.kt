package com.hello.hello_store_service.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("payments")
data class PaymentEntity(
    @Id val paymentId: String? = null,
    val orderNo: String,
    val amount: Long,
    val currency: String? = null,
    val payStatus: Int,
    val payType: String? = null,
    val payWay: String? = null,
    val payWayName: String? = null,
    val traceNo: String? = null,
    val channelTrxNo: String? = null,
    val interactId: String? = null,
    val period: String? = null,
    val payTime: String? = null,
    val paySuccessTime: String? = null
)
