package com.hello.hello_store_service.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * 商品实体类，对应 MongoDB 中的 "goods" 集合
 */
@Document("goods")
data class Goods(
    @Id
    val id: String? = null,                  // MongoDB 主键 ID（自动生成）
    val spuId: String,                       // 商品 SPU ID（标准化产品单元）
    val saasId: String,                      // SaaS 平台 ID，用于区分租户
    val storeId: String,                     // 店铺 ID
    val title: String,                       // 商品标题
    val primaryImage: String,                // 主图 URL
    val images: List<String>,                // 商品图片列表
    val video: String? = null,               // 商品视频 URL
    val available: Int? = null,              // 可用状态（可能是上架/下架/库存可售等）
    val minSalePrice: Int,                   // 最低销售价（分/元）
    val minLinePrice: Int,                   // 最低划线价（原价）
    val maxSalePrice: Int,                   // 最高销售价
    val maxLinePrice: Int,                   // 最高划线价
    val spuStockQuantity: Int,               // SPU 库存总量
    val soldNum: Int,                        // 已售数量
    val isPutOnSale: Int,                    // 是否上架（1 上架，0 下架）
    val categoryIds: List<String>? = emptyList(), // 分类 ID 列表
    val groupIdList: List<String>? = emptyList(), // 商品分组 ID 列表
    val specList: List<Spec>,                // 规格列表（如颜色、尺寸）
    val skuList: List<Sku>,                  // SKU 列表（具体的商品单元）
    val spuTagList: List<SpuTag>,            // 标签列表（如“热销”、“新品”）
    val limitInfo: List<LimitInfo>? = null,    // 限购信息
    val desc: List<String>,                  // 商品详情描述（通常是富文本/图片）
    val etitle: String                       // 商品英文标题
)

/**
 * 商品规格，例如 "颜色"
 */
data class Spec(
    val specId: String,                      // 规格 ID
    val title: String,                       // 规格标题（如“颜色”）
    val specValueList: List<SpecValue>       // 规格值列表（如“红色”、“蓝色”）
)

/**
 * 规格的具体取值
 */
data class SpecValue(
    val specValueId: String,                 // 规格值 ID
    val specId: String? = null,              // 对应的规格 ID
    val saasId: String? = null,              // SaaS 平台 ID
    val specValue: String,                   // 规格值内容（如“红色”）
    val image: String? = null                // 规格图片（如“红色”的样例图）
)

/**
 * 商品 SKU（库存单位），每个 SKU 是一个具体的可售商品
 */
data class Sku(
    val skuId: String,                       // SKU ID
    val skuImage: String? = null,            // SKU 图片
    val specInfo: List<SpecInfo>,            // SKU 对应的规格信息
    val priceInfo: List<PriceInfo>,          // SKU 价格信息
    val stockInfo: StockInfo,                // SKU 库存信息
    val weight: Weight? = null,              // 重量信息
    val volume: Any? = null,                 // 体积信息
    val profitPrice: Int? = null             // 利润价
)

/**
 * SKU 的规格信息，例如 "颜色: 红色"
 */
data class SpecInfo(
    val specId: String,                      // 规格 ID
    val specTitle: String? = null,           // 规格标题（如“颜色”）
    val specValueId: String,                 // 规格值 ID
    val specValue: String? = null            // 规格值（如“红色”）
)

/**
 * SKU 的价格信息
 */
data class PriceInfo(
    val priceType: Int,                      // 价格类型（如 1=销售价，2=原价）
    val price: Int,                          // 价格值
    val priceTypeName: String? = null        // 价格类型名称
)

/**
 * SKU 的库存信息
 */
data class StockInfo(
    val stockQuantity: Int,                  // 库存数量
    val safeStockQuantity: Int,              // 安全库存（预警线）
    val soldQuantity: Int                    // 已售数量
)

/**
 * 商品重量
 */
data class Weight(
    val value: Any? = null,                  // 重量数值
    val unit: String                         // 单位（如 "kg"）
)

/**
 * 商品标签，例如 "新品"、"热销"
 */
data class SpuTag(
    val id: String? = null,                  // 标签 ID
    val title: String,                       // 标签名称
    val image: String? = null                // 标签图标
)

/**
 * 限购信息
 */
data class LimitInfo(
    val text: String                         // 限购描述（如“每人限购 1 件”）
)
