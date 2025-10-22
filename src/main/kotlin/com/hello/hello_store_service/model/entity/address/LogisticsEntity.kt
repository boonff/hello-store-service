package com.hello.hello_store_service.model.entity.address

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("logistics")
data class LogisticsEntity(
    @Id val id: String,
    val orderNo: String,
    val logisticsType: Int,
    val logisticsNo: String,
    val companyCode: String,
    val companyName: String,
    val status: String,
    val sendTime: String?,
    val arrivalTime: String?,
    val expectArrivalTime: String?,
    val receiverAddressId: String,
    val senderName: String?,
    val senderPhone: String?,
    val senderAddress: String?
)
