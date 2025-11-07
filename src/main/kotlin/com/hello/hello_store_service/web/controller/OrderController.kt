package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.clean.SettleViewClean
import com.hello.hello_store_service.web.model.request.SettleDetailRequest
import com.hello.hello_store_service.web.model.view.SettleView
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("order")
class OrderController(
    private val genSettleDetailClean: SettleViewClean
) {
    @PostMapping("/detail")
    fun genSettleDetail(
        @RequestBody request: SettleDetailRequest
    ): SettleView {
        val username = SecurityUtils.currentUsername()
        return genSettleDetailClean.getSettleDetailView(request, username)
    }
}
