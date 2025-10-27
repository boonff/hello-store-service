package com.hello.hello_store_service.model.view.settle

import com.hello.hello_store_service.model.business.goods.SpecDetailsBO
import com.hello.hello_store_service.model.business.settle.SkuSettleBO
import com.hello.hello_store_service.model.view.goods.SpecDetail

data class SkuSettleDetail(
    val skuId: String,                  // SKU ID
    val roomId: String,                 // 房间 ID（用于直播或活动）
    val egoodsName: String,             // 英文商品名
    val goodsName: String,              // 商品名
    val image: String?,                  // 商品图片
    val masterGoodsType: Int,           // 主商品类型
    val promotionIds: List<String>?,           // 促销活动 ID 列表（逗号分隔）
    val quantity: Int,                  // 购买数量
    val oriPrice: Int,                  // 原价
    val payPrice: Int,                  // 实付价
    val discountSettlePrice: Int,       // 折后结算价
    val realSettlePrice: Int,           // 实际结算价
    val reminderStock: Int,             // 库存提醒阈值
    val settlePrice: Int,               // 结算价
    val skuSpecLst: List<SpecDetail>    // 规格详情列表
) {
    companion object {
        fun from(skuBO: SkuSettleBO,
                 specBO: SpecDetailsBO
        ): SkuSettleDetail =
            SkuSettleDetail(
                skuId = skuBO.spuId(),
                roomId = "null",
                egoodsName = skuBO.egoodsName(),
                goodsName = skuBO.goodsName(),
                image = skuBO.skuEntity.skuImage,
                masterGoodsType = 0, //TODO 不清楚的字段
                promotionIds = skuBO.promotionIds(),
                quantity = skuBO.quantity(),
                oriPrice = skuBO.oriFee().toInt(), //TODO 修改类型转换方法
                payPrice = skuBO.payFee().toInt(),
                discountSettlePrice = skuBO.discountFee().toInt(),
                realSettlePrice = skuBO.realFee().toInt(),
                reminderStock = skuBO.reminderStock(),
                settlePrice = skuBO.settleFee().toInt(),
                skuSpecLst = specBO.fetchSpecDetail()

            )
    }

}