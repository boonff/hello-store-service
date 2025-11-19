package com.hello.hello_store_service.data.entity.order

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("orders")
data class OrderEntity(
    @Id val orderId: String? = null,           // 商户订单号，全局唯一
    @Indexed(unique = true)
    val uid: String,                           // 下单用户ID
    val status: OrderStatus,
    val cancelType: ServiceType? = null,
    val cancelReasonType: ServiceReceiptStatus? = null,
    val cancelReason: String? = null,
    val rightsType: ServiceStatus? = null,
    val goodsQuantity:Int,
    val totalFee: Int,                        // 订单总金额（分）
    val discountFee: Int,                     // 优惠金额（分）
    val couponFee: Int,
    val saleFee: Int,
    val paymentFee: Int,                      // 实付金额（分）
    val deliveryFee: Int,                     // 运费（分）
    val remark: String? = null,                // 买家留言或备注


    val paymentId: String? = null,             // 支付流水号或支付系统返回ID
    val logisticsId: String? = null,           // 物流信息ID
    val addressId: String? = null,             // 收货地址ID
    val orderItems: List<OrderItem>,           // 订单商品明细

    val autoCancelTime: LocalDateTime? = null,          // 自动取消时间，时间戳，可为空
    val createTime: LocalDateTime,                      // 下单时间，时间戳
    val updateTime: LocalDateTime,                      // 订单最后更新时间，时间戳
)


data class OrderItem(
    val skuId: String,                         // 商品SKU ID
    val spuId: String,                         // 商品SPU ID
    val skuImage: String?,                     // 商品图片URL
    val saleFee: Int,                          // 单价（分）
    val quantity: Int                          // 数量
)