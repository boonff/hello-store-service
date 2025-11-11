package com.hello.hello_store_service.application.order

import com.hello.hello_store_service.application.coupon.OrderCouponRule
import com.hello.hello_store_service.data.service.goods.SkuDataService
import org.springframework.stereotype.Service

@Service
class OrderRule(
    private val skuService: SkuDataService,
    private val orderCouponCalculator: OrderCouponRule
) {
    fun goodsCount(param: OrderParam): Int = param.skuList.size
    fun packageCount(param: OrderParam): Int = 0 //TODO 计算包裹数量

    fun totalFee(param: OrderParam): Int {
        return param.skuList.sumOf { (skuId, quantity) ->
            fetchSkuById(skuId)?.let { skuEntity ->
                skuEntity.salePrice * quantity
            } ?: 0
        }
    }

    fun discountFee(param: OrderParam): Int = couponFee(param)

    fun couponFee(param: OrderParam): Int {
        if (param.couponIdList.isNullOrEmpty()) return 0

        return discountCoupons(
            param.username,
            param.couponIdList,
            totalFee(param)
        )
    }

    fun saleFee(param: OrderParam): Int =
        totalFee(param) - couponFee(param)

    fun deliveryFee(): Int = 0 //TODO 计算运费

    fun payFee(param: OrderParam): Int =
        saleFee(param) + deliveryFee()

    private fun discountCoupons(
        username: String,
        couponIds: List<String>,
        originalPrice: Int
    ): Int = orderCouponCalculator.discountCoupons(username, couponIds, originalPrice)

    private fun fetchSkuById(skuId: String) = skuService.fetchById(skuId)
}