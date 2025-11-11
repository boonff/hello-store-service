package com.hello.hello_store_service.web.model.view

import com.hello.hello_store_service.data.model.entity.address.AddressEntity
import com.hello.hello_store_service.web.model.view.goods.SpecDetailView

data class SettleOrderView(
    val settleType: Int,                // 结算类型（如立即购买、购物车结算等）
    val userAddress: AddressEntity?,     // 用户收货地址信息
    val totalGoodsCount: Int,           // 商品总数量
    val packageCount: Int,              // 包裹总数量
    val totalAmount: Int,               // 商品原始总金额
    val totalPayAmount: Int,            // 实际需支付金额
    val totalDiscountAmount: Int,       // 优惠总金额
    val totalPromotionAmount: Int,      // 活动优惠总金额
    val totalCouponAmount: Int,         // 优惠券优惠总金额
    val totalSalePrice: Int,            // 销售价总额（打折后价格，不包括运费）
    val totalGoodsAmount: Int,          // 商品结算金额总计
    val totalDeliveryFee: Int,          // 运费总金额
    val invoiceRequest: Boolean = false,        // 是否需要发票
    val skuImages: List<String>? = null,              // 商品图片（拼接或主图）
    val deliveryFeeList: List<Int>? = null, // 各包裹运费列表
    val storeGoodsList: List<StoreOrderView>    // 店铺商品明细列表
)

data class StoreOrderView(
    val storeId: String,                // 店铺 ID
    val storeName: String,              // 店铺名称
    val remark: String? = null,                    // 备注（可为标志位或备注数量）
    val goodsCount: Int,                // 商品数量
    val deliveryFee: Int,               // 运费
    val deliveryWords: String,          // 运费说明文字
    val storeTotalAmount: Int,          // 店铺商品总金额
    val storeTotalPayAmount: Int,       // 店铺实际支付金额
    val storeTotalDiscountAmount: Int,  // 店铺优惠总额
    val storeTotalCouponAmount: Int,    // 店铺优惠券优惠金额
    val skuDetailVos: List<SkuOrderView>?,        // 商品明细信息
    val couponList: List<String>?       // 可用优惠券列表
)

data class SkuOrderView(
    val skuId: String,                  // SKU ID
    val roomId: String?,                 // 房间 ID（用于直播或活动）
    val egoodsName: String,             // 英文商品名
    val goodsName: String,              // 商品名
    val image: String?,                  // 商品图片
    val masterGoodsType: Int?,           // 主商品类型
    val promotionIds: List<String>?,           // 促销活动 ID 列表（逗号分隔）
    val quantity: Int,                  // 购买数量
    val oriPrice: Int,                  // 原价
    val payPrice: Int,                  // 实付价
    val discountSettlePrice: Int,       // 折后结算价
    val realSettlePrice: Int,           // 实际结算价
    val reminderStock: Int,             // 库存提醒阈值
    val settlePrice: Int,               // 结算价
    val skuSpecLst: List<SpecDetailView>    // 规格详情列表
)
