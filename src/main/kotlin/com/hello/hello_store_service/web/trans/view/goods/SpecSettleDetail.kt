package com.hello.hello_store_service.web.trans.view.goods

data class SpecSettleDetail(
    val spuId: String,                  // SPU ID
    val storeId: String,                // 店铺 ID
    val tagPrice: Int,                  // 标签价
    val tagText: Int,                   // 标签文本（或枚举值）
    val totalSkuPrice: Int,             // SKU 总价
    val unit: String,                   // 单位（如件、箱）
    val viceGoodsType: Int,             // 副商品类型
    val volume: Int,                    // 体积
    val weight: Int                     // 重量
)