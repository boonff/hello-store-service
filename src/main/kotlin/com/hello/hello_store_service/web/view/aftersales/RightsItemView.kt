package com.hello.hello_store_service.web.view.aftersales

import com.hello.hello_store_service.web.view.SpecInfo

data class RightsItemView(
    val actualPrice: Long,
    val createTime: String,
    val disconutInfo: String,
    val goodsName: String,
    val goodsPictureUrl: String,
    val goodsViceType: Int,
    val itemDiscountAmount: Long,
    val itemRefundAmount: Long,
    val itemStatus: Int,
    val itemTotalAmount: Long,
    val orderNo: String,
    val parentOrderNo: String,
    val rightsId: Long,
    val rightsNo: String,
    val rightsParentNo: String,
    val rightsQuantity: Int,
    val saasId: Int,
    val skuId: Long,
    val specInfo: List<SpecInfo>,
    val updateTime: String
)