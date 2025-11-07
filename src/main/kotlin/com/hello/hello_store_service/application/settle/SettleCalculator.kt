package com.hello.hello_store_service.application.settle

import com.hello.hello_store_service.application.coupon.OrderCouponCalculator
import com.hello.hello_store_service.application.coupon.UserCouponService
import com.hello.hello_store_service.data.service.goods.SkuDataService
import org.springframework.stereotype.Service

@Service
class SettleCalculator(
    private val skuService: SkuDataService,
    private val orderCouponCalculator: OrderCouponCalculator
) {
    fun goodsCount(param: SettleParam): Int = param.skuList.size
    fun packageCount(param: SettleParam): Int = 0 //TODO 计算包裹数量

    fun totalFee(param: SettleParam): Int {
        return param.skuList.sumOf { (skuId, quantity) ->
            fetchSkuById(skuId)?.let { skuEntity ->
                skuEntity.salePrice * quantity
            } ?: 0
        }
    }

    fun discountFee(param: SettleParam): Int = couponFee(param)

    fun couponFee(param: SettleParam): Int {
        if (param.couponIdList.isNullOrEmpty()) return 0

        return discountCoupons(
            param.username,
            param.couponIdList,
            totalFee(param)
        )
    }

    fun saleFee(param: SettleParam): Int =
        totalFee(param) - couponFee(param)

    fun deliveryFee(): Int = 0 //TODO 计算运费

    fun payFee(param: SettleParam): Int =
        saleFee(param) + deliveryFee()

    private fun discountCoupons(
        username: String,
        couponIds: List<String>,
        originalPrice: Int
    ): Int = orderCouponCalculator.discountCoupons(username, couponIds, originalPrice)

    private fun fetchSkuById(skuId: String) = skuService.fetchById(skuId)
}