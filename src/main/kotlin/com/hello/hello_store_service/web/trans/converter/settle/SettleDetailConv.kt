package com.hello.hello_store_service.web.trans.converter.settle

import com.hello.hello_store_service.web.trans.context.SettleParams
import com.hello.hello_store_service.web.trans.view.settle.SettleDetail
import com.hello.hello_store_service.web.trans.view.settle.StoreSettleDetail

object SettleDetailConv {
    fun convert(settleParams: SettleParams): SettleDetail =
        SettleDetail.from(
            bo = settleParams.settleDetailBO,
            storeGoodsSettle = convertStoreSettles(settleParams),
            userAddress = settleParams.userAddress
        )


    private fun convertStoreSettles(
        settleParams: SettleParams
    ): List<StoreSettleDetail> = StoreSettlesConv.convert(settleParams)
}