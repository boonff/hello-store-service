package com.hello.hello_store_service.web.trans.converter.settle

import com.hello.hello_store_service.web.trans.business.SpecDetailsBO
import com.hello.hello_store_service.web.trans.business.settle.SkuSettleBO
import com.hello.hello_store_service.web.trans.view.settle.SkuSettleDetail

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
            oriPrice = skuBO.oriFee(),
            payPrice = skuBO.payFee(),
            discountSettlePrice = skuBO.discountFee(),
            realSettlePrice = skuBO.realFee(),
            reminderStock = skuBO.reminderStock(),
            settlePrice = skuBO.settleFee(),
            skuSpecLst = specBO.fetchSpecDetail()

        )
}