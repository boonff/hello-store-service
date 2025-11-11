package com.hello.hello_store_service.pay

import jakarta.servlet.http.HttpServletRequest

/**
 * 支付服务接口，定义支付相关核心功能
 */
interface PayService {

    /**
     * 创建支付订单
     * @param orderNo 商户订单号
     * @param amount 支付金额（分为单位）
     * @param openid 用户微信openid（小程序支付）
     * @param description 商品描述
     * @return 返回给前端调用 wx.requestPayment 所需参数
     */
    fun createOrder(
        orderNo: String,
        amount: Int,
        openid: String,
        description: String
    ): Map<String, String>

    /**
     * 支付回调处理
     * @param request 微信回调的 HttpServletRequest
     * @return 返回微信要求的成功或失败响应
     */
    fun handleNotify(request: HttpServletRequest): String

    /**
     * 查询支付状态（可选）
     * @param orderNo 商户订单号
     * @return 支付状态，如 "SUCCESS", "NOTPAY", "CLOSED"
     */
    fun queryOrder(orderNo: String): String

    /**
     * 关闭未支付订单（可选）
     * @param orderNo 商户订单号
     */
    fun closeOrder(orderNo: String)
}
