package com.hello.hello_store_service.web.trans.business.settle

/*TODO
   优惠卷的设定还没有确定，打算不做商店限制，优惠卷可以随便用。
   并且现在 SettleDetailBO 和 StoreSettleBO 类的计算方法有问题。
 */
class OrderSettlement(
    private val skuList: List<SkuQuantityBO>,
    private val couponList: List<CouponStoreBO>?
) {
    fun goodsCount(): Int = skuList.size
    fun payFee(): Int {
        return saleFee() + deliveryFee()
    }

    fun totalFee(): Int {
        var total = 0
        skuList.forEach { skuItem ->
            total += skuItem.totalPrice
        }

        return total
    }

    fun couponFee(): Int {
        var total = 0
        couponList?.forEach { couponItem ->
            total = applyCoupon(totalFee(), couponItem.coupon)
        }

        return total
    }

    //TODO 计算活动优惠金额
    fun promotionFee(): Int = 0

    fun discountFee(): Int = couponFee() + promotionFee()

    fun saleFee(): Int = totalFee() - couponFee()

    fun deliveryFee(): Int = 0



}


