package com.hello.hello_store_service.model.entity.order

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("orders")
data class OrderEntity(
    @Id val id: String,
    val orderNo: String,
    val uid: String,
    val storeId: String,
    val saasId: String,
    val status: Int,
    val type: Int,
    val subType: Int,
    val totalAmount: Long,
    val discountAmount: Long,
    val paymentAmount: Long,
    val freightFee: Long,
    val remark: String?,
    val createTime: Long,
    val updateTime: Long,
    val autoCancelTime: Long?,
    val paymentId: String?,
    val logisticsId: String?,
    val addressId: String?,
    val orderItems: List<OrderItemEntity>,
    val channel: ChannelInfo
)

data class OrderItemEntity(
    val skuId: String,
    val spuId: String,
    val goodsName: String,
    val pictureUrl: String,
    val price: Long,
    val quantity: Int
)

data class ChannelInfo(
    val type: Int,
    val source: String,
    val identity: String
)
