package com.hello.hello_store_service.model.converter.settle

import com.hello.hello_store_service.model.business.goods.SpecDetailsBO
import com.hello.hello_store_service.model.business.settle.SkuSettleBO
import com.hello.hello_store_service.model.view.settle.SkuSettleDetail

object SkuSettleConv {
    fun convert(
        skuBO: SkuSettleBO,
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