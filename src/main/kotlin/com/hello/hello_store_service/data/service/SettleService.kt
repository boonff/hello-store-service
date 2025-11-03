package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.application.SettleDetailAssembler
import com.hello.hello_store_service.web.trans.converter.settle.SettleDetailConv
import com.hello.hello_store_service.web.trans.param.SettleDetailRequest
import com.hello.hello_store_service.web.trans.view.settle.SettleDetail
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