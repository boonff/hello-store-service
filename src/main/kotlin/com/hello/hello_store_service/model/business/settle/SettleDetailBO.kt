package com.hello.hello_store_service.model.business.settle

import com.hello.hello_store_service.model.entity.activity.CouponEntity
import com.hello.hello_store_service.model.entity.activity.CouponType
import com.hello.hello_store_service.model.transfer.payment.CouponRef
import com.hello.hello_store_service.model.transfer.payment.SkuData
import java.math.BigDecimal

/*TODO
   优惠卷的设定还没有确定，打算不做商店限制，优惠卷可以随便用。
   并且现在 SettleDetailBO 和 StoreSettleBO 类的计算方法有问题。
 */
class SettleDetailBO(
    private val skuList: List<SkuQuantityBO>,
    private val couponList: List<CouponStoreBO>?
) {
    fun goodsCount(): Int = skuList.size
    fun payFee(): BigDecimal {
        return saleFee()
            .subtract(deliveryFee())
    }

    fun totalFee(): BigDecimal {
        var total = BigDecimal.ZERO
        skuList.forEach { skuItem ->
            total = total.add(skuItem.totalPrice)
        }

        return total
    }

    fun couponFee(): BigDecimal {
        var total = BigDecimal.ZERO
        couponList?.forEach { couponItem ->
            total = applyCoupon(total, couponItem.coupon)
        }

        return total
    }

    //TODO 计算活动优惠金额
    fun promotionFee(): BigDecimal = BigDecimal.ZERO

    fun discountFee(): BigDecimal =
        couponFee()
            .add(promotionFee())

    fun saleFee(): BigDecimal =
        totalFee()
            .subtract(couponFee())

    fun deliveryFee(): BigDecimal = BigDecimal.ZERO

    private fun applyCoupon(total: BigDecimal, coupon: CouponEntity): BigDecimal {
        // 判断门槛
        if (total < coupon.threshold) return total
        // 根据优惠类型计算
        return when (coupon.type) {
            CouponType.Discount -> {
                val result = total.subtract(coupon.discount)
                if (result < BigDecimal.ZERO) BigDecimal.ZERO else result
            }

            CouponType.PriceOff -> {
                total.multiply(coupon.discountRate?.toBigDecimal())
            }
        }
    }
}


