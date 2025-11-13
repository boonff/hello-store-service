package com.hello.hello_store_service.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("orders")
data class OrderEntity(
    @Id val orderId: String? = null,                      // 商户订单号，全局唯一
    val uid: String,                          // 下单用户ID
    val status: Int,                           // 订单状态（如：0未支付，1已支付，2已取消等）
    val type: Int,                             // 订单类型（业务自定义，例如普通订单/拼团订单）
    val subType: Int,                          // 子类型（业务自定义，可进一步区分）
    val totalAmount: Long,                     // 订单总金额（分）
    val discountAmount: Long,                  // 优惠金额（分）
    val paymentAmount: Long,                   // 实付金额（分） = totalAmount - discountAmount + deliveryFee
    val deliveryFee: Long,                      // 运费（分）
    val remark: String? = null,                        // 买家留言或备注
    val createTime: Long,                      // 下单时间，时间戳
    val updateTime: Long,                      // 订单最后更新时间，时间戳
    val autoCancelTime: Long? = null,                 // 自动取消时间，时间戳，可为空
    val paymentId: String? = null,                     // 支付流水号或支付系统返回ID
    val logisticsId: String? = null,                   // 物流信息ID
    val addressId: String? = null,                     // 收货地址ID
    val orderItems: List<OrderItemEntity>,      // 订单商品明细
    val channel: ChannelInfo                     // 订单来源渠道信息
)

data class OrderItemEntity(
    val skuId: String,                         // 商品SKU ID
    val spuId: String,                         // 商品SPU ID
    val goodsName: String,                     // 商品名称
    val pictureUrl: String,                    // 商品图片URL
    val price: Long,                           // 单价（分）
    val quantity: Int                           // 数量
)

data class ChannelInfo(
    val type: Int,                             // 渠道类型（如：0小程序，1APP，2H5等）
    val source: String,                        // 来源标识（如微信、京东等）
    val identity: String                        // 用户渠道身份标识（如openid、userId等）
)
