package com.hello.hello_store_service.web.trans.view.coupon

import com.hello.hello_store_service.web.trans.business.coupon.CouponDomain
import java.time.format.DateTimeFormatter

data class CouponResultList(
    val couponDataList: List<CouponData>,
    val reduce: Int
) {
    companion object {
        fun from(bos: List<CouponDomain>) =
            CouponResultList(
                couponDataList = bos.map { bo ->
                    CouponData.from(bo)
                },
                reduce = 0
            )
    }
}

data class CouponData(
    val couponVO: CouponView,
    val status: Boolean
) {
    companion object {
        fun from(bo: CouponDomain) =
            CouponData(
                couponVO = CouponView.from(
                    bo
                ),
                status = false,
            )
    }
}

data class CouponView(
    val storeId: String,
    val condition: String,
    val couponId: String,
    val startTime: String,
    val endTime: String,
    val name: String,
    val profit: String,
    val promotionCode: String,
    val promotionSubCode: String,
    val scopeText: String,
    val value: Int,
    val type: Int,
) {
    companion object {
        fun from(bo: CouponDomain): CouponView {
            val (start, end) = bo.timeLimit
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            return CouponView(
                storeId = "TODO",
                condition = bo.coupon.title,
                couponId = bo.coupon.couponId,
                endTime = end.format(formatter),
                startTime = start.format(formatter),
                name = bo.coupon.description,
                profit = "TODO",
                promotionCode = "TODO",
                promotionSubCode = "TODO",
                scopeText = "TODO",
                value = bo.displayValue,
                type = bo.type.code
            )
        }
    }
}
