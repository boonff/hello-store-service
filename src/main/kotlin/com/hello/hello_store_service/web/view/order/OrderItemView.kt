package com.hello.hello_store_service.web.view.order

data class OrderItemView(
    val id: String,                     // 商品条目ID
    val orderNo: String?,               // 订单号
    val spuId: String,                  // 商品SPU ID
    val skuId: String,                  // 商品SKU ID
    val roomId: String?,                // 房间ID（若有）
    val goodsMainType: Int,             // 商品主类型（分类标识）
    val goodsViceType: Int,             // 商品副类型（细分类标识）
    val goodsName: String,              // 商品名称
    val goodsPictureUrl: String,        // 商品图片URL
    val originPrice: String,            // 商品原价（分为单位）
    val actualPrice: String,            // 商品实际价格（分为单位）
    val specifications: List<Specification>, // 商品规格列表（颜色、尺寸等）
    val buyQuantity: Int,               // 购买数量
    val itemTotalAmount: String,        // 商品总金额（分为单位）
    val itemDiscountAmount: String,     // 商品折扣金额（分为单位）
    val itemPaymentAmount: String,      // 商品应付金额（分为单位）
    val goodsPaymentPrice: String,      // 商品支付价格（分为单位）
    val tagPrice: String?,              // 标签价格
    val tagText: String?,               // 标签文本
    val outCode: String?,               // 外部编码
    val labelVOs: List<Any>?,           // 商品标签列表
    val buttonVOs: List<Any>?           // 商品操作按钮列表
)

data class Specification(
    val specTitle: String,              // 规格名称（如颜色、尺寸）
    val specValue: String               // 规格值（如米色、S码）
)
