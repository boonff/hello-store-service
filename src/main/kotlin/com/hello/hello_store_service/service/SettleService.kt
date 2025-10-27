package com.hello.hello_store_service.service

import com.hello.hello_store_service.application.SettleDetailAssembler
import com.hello.hello_store_service.model.business.settle.SettleDetailBO
import com.hello.hello_store_service.model.business.settle.StoreSettleBO
import com.hello.hello_store_service.model.business.settle.toCouponStoreBO
import com.hello.hello_store_service.model.business.settle.toSkuQuantityBO
import com.hello.hello_store_service.model.converter.settle.SettleDetailConv
import com.hello.hello_store_service.model.converter.settle.StoreSettlesConv
import com.hello.hello_store_service.model.entity.address.AddressEntity
import com.hello.hello_store_service.model.entity.goods.SkuEntity
import com.hello.hello_store_service.model.transfer.payment.SettleDetailRequest
import com.hello.hello_store_service.model.view.settle.SettleDetail
import com.hello.hello_store_service.model.view.settle.StoreSettleDetail
import com.hello.hello_store_service.service.activity.CouponService
import com.hello.hello_store_service.service.address.AddressService
import com.hello.hello_store_service.service.goods.SkuService
import com.hello.hello_store_service.service.goods.SpuService
import com.hello.hello_store_service.service.store.StoreService
import org.springframework.stereotype.Service

@Service
class SettleService(
    private val settleDetailAssembler: SettleDetailAssembler
) {
    fun genSettleDetail(request: SettleDetailRequest): SettleDetail {
        val settleParams = settleDetailAssembler.build(request)

        return SettleDetailConv.convert(settleParams)
    }

}