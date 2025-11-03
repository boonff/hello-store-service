package com.hello.hello_store_service.data.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("payments")
data class PaymentEntity(
    @Id val id: String,
    val orderNo: String,
    val amount: Long,
    val currency: String?,
    val payStatus: Int,
    val payType: String?,
    val payWay: String?,
    val payWayName: String?,
    val traceNo: String?,
    val channelTrxNo: String?,
    val interactId: String?,
    val period: String?,
    val payTime: String?,
    val paySuccessTime: String?
)
