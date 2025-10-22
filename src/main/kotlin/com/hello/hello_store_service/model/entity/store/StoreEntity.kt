package com.hello.hello_store_service.model.entity.store

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("stores")
data class StoreEntity(
    @Id
    val id: String? = null,
    val storeId: String? = null,           // 商铺ID

    val storeName: String,                  // 商铺名称
    val storeType: String? = null,          // 商铺类型，例如餐饮、服饰等
    val storeStatus: Int = 1,               // 状态：0-关闭，1-正常

    val storeLogo: String? = null,          // 商铺logo或图片URL
    val storeDescription: String? = null,   // 简短介绍

    val contactPhone: String? = null,       // 联系电话
    val contactEmail: String? = null,       // 联系邮箱
    val address: String? = null,            // 地址
    val location: GeoLocation? = null,      // 经纬度位置

    val rating: Double = 0.0,               // 用户评分
    val reviewCount: Int = 0,               // 评论数量
    val spuIds: List<String>? = null,   // 商铺商品ID列表

    val deliverySupported: Boolean = false, // 是否支持配送
    val pickupSupported: Boolean = false,   // 是否支持自提
    val openTime: String? = null,           // 营业时间，例如 "09:00-21:00"

    val createTime: LocalDateTime = LocalDateTime.now(),  // 创建时间
    val updateTime: LocalDateTime = LocalDateTime.now()   // 更新时间
)

// 经纬度信息封装
data class GeoLocation(
    val latitude: Double,
    val longitude: Double
)
