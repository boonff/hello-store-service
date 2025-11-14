package com.hello.hello_store_service.application.order

import com.hello.hello_store_service.application.coupon.OrderCouponRule
import com.hello.hello_store_service.data.service.goods.SkuDataService
import com.hello.hello_store_service.web.request.OrderRequest
import org.springframework.stereotype.Service

@Service
class OrderRule(
    private val skuService: SkuDataService,
    private val orderCouponRule: OrderCouponRule
) {
    fun goodsCount(param: OrderRequest): Int = param.skuList.size

    fun totalFee(param: OrderRequest): Int {
        return param.skuList.sumOf { (skuId, quantity) ->
            fetchSkuById(skuId)?.let { skuEntity ->
                skuEntity.salePrice * quantity
            } ?: 0
        }
    }

    fun discountFee(uid: String, param: OrderRequest): Int = couponFee(uid, param)

    fun couponFee(uid: String, param: OrderRequest): Int {
        if (param.couponIdList.isNullOrEmpty()) return 0

        return discountCoupons(
            uid,
            param.couponIdList,
            totalFee(param)
        )
    }

    fun saleFee(uid: String, param: OrderRequest): Int =
        totalFee(param) - couponFee(uid, param)

    fun paymentFee(uid: String, param: OrderRequest): Int =
        saleFee(uid, param) + deliveryFee(param)

    fun packageCount(param: OrderRequest): Int = 0 //TODO 计算包裹数量

    fun deliveryFee(param: OrderRequest): Int = 0 //TODO 计算运费


    private fun discountCoupons(
        uid: String,
        couponIds: List<String>,
        originalPrice: Int
    ): Int = orderCouponRule.discountCoupons(uid, couponIds, originalPrice)

    private fun fetchSkuById(skuId: String) = skuService.fetchById(skuId)
}