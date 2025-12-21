package com.hello.hello_store_service.data.entity.address

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("logistic")
data class LogisticEntity(
    @Id val logisticId: String? = null,
    @Indexed(unique = true) val orderId: String,
    val logisticNo: String,                 // 运单号
    val companyCode: String,                 // 物流公司编码
    val companyName: String,                 // 物流公司名称
    val status: LogisticStatus,                      // 当前物流状态（如：已发货/运输中/已签收）
    val timeInfo: LogisticTime,
    val receiverAddressId: String,           // 收件地址ID（关联收货地址）
    val senderName: String? = null,          // 寄件人姓名
    val senderPhone: String? = null,         // 寄件人电话
    val senderAddress: String? = null        // 寄件人地址
)

data class LogisticTime(
    val sendTime: LocalDateTime,            // 发货时间
    val arrivalTime: LocalDateTime,         // 实际到达时间
    val expectArrivalTime: LocalDateTime,   // 预计到达时间
    val signTime: LocalDateTime             //签收时间
)

enum class LogisticStatus(val code: Int, val desc: String) {
    CREATED(0, "未揽收"),
    PICKED_UP(1, "已揽收"),
    IN_TRANSIT(2, "运输中"),
    OUT_FOR_DELIVERY(3, "派送中"),
    DELIVERED(4, "已签收"),
    RETURNED(5, "退回"),
}
