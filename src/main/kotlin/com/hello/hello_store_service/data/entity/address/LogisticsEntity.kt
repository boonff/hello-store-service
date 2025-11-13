package com.hello.hello_store_service.data.entity.address

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("logistics")
data class LogisticsEntity(
    @Id val logisticsId: String? = null,
    val orderNo: String,
    val logisticsType: Int,
    val logisticsNo: String,
    val companyCode: String,
    val companyName: String,
    val status: String,
    val sendTime: String? = null,
    val arrivalTime: String? = null,
    val expectArrivalTime: String? = null,
    val receiverAddressId: String,
    val senderName: String? = null,
    val senderPhone: String? = null,
    val senderAddress: String? = null
)
