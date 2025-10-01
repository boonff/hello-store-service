package com.hello.hello_store_service.model.entity.goods

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("goods")
data class Goods(
    @Id
    val id: String? = null,
    val spuId: String,                       // SPU ID
    val saasId: String,                      // SaaS 平台 ID
    val storeId: String,                     // 店铺 ID
    val title: String,                       // 商品标题
    val etitle: String,                      // 英文标题
    val primaryImage: String,                // 主图
    val images: List<String>,                // 轮播图
    val video: String? = null,               // 视频
    val categoryIds: List<String>? = emptyList(), // 分类
    val groupIdList: List<String>? = emptyList(), // 分组
    val spuTagList: List<SpuTag> = emptyList(),   // 标签
    val desc: List<String> = emptyList(),         // 商品描述（图文详情）
    val available: Int? = null,               // 是否可售
    val minSalePrice: Int? = null,            // 最低参考售价（分）
    val maxSalePrice: Int? = null,             // 最高参考售价（分）
    val maxLinePrice: Int? = null
)

data class SpuTag(
    val id: String? = null,
    val title: String,
    val image: String? = null
)
