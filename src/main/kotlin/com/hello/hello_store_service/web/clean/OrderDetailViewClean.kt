package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.sku.SkuService
import com.hello.hello_store_service.application.sku.SpecDetail
import com.hello.hello_store_service.application.order.OrderService
import com.hello.hello_store_service.data.entity.order.OrderItem
import com.hello.hello_store_service.data.entity.order.OrderStatus
import com.hello.hello_store_service.data.service.goods.SpuDataService
import com.hello.hello_store_service.utils.TimeUtil
import com.hello.hello_store_service.web.request.OrderRequest
import com.hello.hello_store_service.web.view.order.ButtonView
import com.hello.hello_store_service.web.view.order.LogisticsView
import com.hello.hello_store_service.web.view.order.OrderButtonTypes
import com.hello.hello_store_service.web.view.order.OrderDetailView
import com.hello.hello_store_service.web.view.order.OrderItemView
import com.hello.hello_store_service.web.view.order.PaymentView
import com.hello.hello_store_service.web.view.order.Specification
import org.springframework.stereotype.Service

@Service
class OrderDetailViewClean(
    private val orderService: OrderService,
    private val spuDataService: SpuDataService,
    private val skuDataService: SkuService
) {
    fun fetchOrderDetailView(orderId: String): OrderDetailView? {
        val item = orderService.fetchById(orderId) ?: return null
        if (item.orderId == null) return null
        return OrderDetailView(
            uid = item.uid,
            orderId = item.orderId,
            orderStatus = item.status.code,
            orderStatusName = item.orderId,
            totalAmount = item.totalFee,
            goodsAmountApp = item.saleFee,
            goodsAmount = item.saleFee,
            paymentAmount = item.paymentFee,
            freightFee = item.deliveryFee,
            discountAmount = item.discountFee,
            remark = item.remark ?: "无",
            cancelType = item.cancelType?.code,
            cancelReasonType = item.cancelReasonType?.code,
            cancelReason = item.cancelReason,
            rightsType = item.rightsType?.code,
            createTime = TimeUtil.toMillis(item.createTime),
            orderItemVOs = fetchOrderItemViews(item.orderId, item.orderItems),
            logisticsVO = fetchLogisticsView(),
            paymentVO = fetchPaymentView(item.paymentFee),
            buttonVOs = fetchButtonViews(item.status),
            labelVOs = null,
            invoiceVO = null,
            couponAmount = item.couponFee,
            autoCancelTime = TimeUtil.minutesAfterMillis(600),
        )
    }

    fun fetchOrderDetailViews(uid: String, orderRequest: OrderRequest): List<OrderDetailView> {
        return orderService.fetchOrderByRange(
            uid,
            orderRequest.pageSize,
            orderRequest.pageNum,
            OrderStatus.fromCode(orderRequest.orderStatus)
        ).mapNotNull { item ->
            if (item.orderId == null) return@mapNotNull null
            if (orderRequest.orderStatus == null || orderRequest.orderStatus == item.status.code)
                OrderDetailView(
                    uid = item.uid,
                    orderId = item.orderId,
                    orderStatus = item.status.code,
                    orderStatusName = item.orderId,
                    totalAmount = item.totalFee,
                    goodsAmountApp = item.saleFee,
                    goodsAmount = item.saleFee,
                    paymentAmount = item.paymentFee,
                    freightFee = item.deliveryFee,
                    discountAmount = item.discountFee,
                    remark = item.remark ?: "无",
                    cancelType = item.cancelType?.code,
                    cancelReasonType = item.cancelReasonType?.code,
                    cancelReason = item.cancelReason,
                    rightsType = item.rightsType?.code,
                    createTime = TimeUtil.toMillis(item.createTime),
                    orderItemVOs = fetchOrderItemViews(item.orderId, item.orderItems),
                    logisticsVO = fetchLogisticsView(),
                    paymentVO = fetchPaymentView(item.paymentFee),
                    buttonVOs = fetchButtonViews(item.status),
                    labelVOs = null,
                    invoiceVO = null,
                    couponAmount = item.couponFee,
                    autoCancelTime = TimeUtil.minutesAfterMillis(600),
                )
            else null
        }
    }

    private fun fetchOrderItemViews(orderId: String, orderItems: List<OrderItem>): List<OrderItemView> {
        return orderItems.mapNotNull { item ->
            val spuEntity = spuDataService.fetchById(item.spuId) ?: return@mapNotNull null
            val skuEntity = skuDataService.fetchSkuModel(item.skuId) ?: return@mapNotNull null
            OrderItemView(
                id = orderId,
                orderNo = orderId,
                spuId = item.spuId,
                skuId = item.skuId,
                goodsName = spuEntity.title,
                goodsPictureUrl = item.skuImage,
                originPrice = skuEntity.salePrice,
                actualPrice = item.saleFee,
                specifications = transSpecifications(skuEntity.specList),
                buyQuantity = item.quantity,
                itemTotalAmount = item.saleFee,
                itemDiscountAmount = 0,
                itemPaymentAmount = 0,
                goodsPaymentPrice = 0,
                tagPrice = 0,
                tagText = null,
                outCode = null,
                labelVOs = null,
                buttonVOs = null
            )
        }
    }

    private fun fetchLogisticsView(): LogisticsView {
        return LogisticsView(
            logisticsType = 1,
            logisticsNo = "",
            logisticsStatus = null,
            logisticsCompanyCode = "",
            logisticsCompanyName = "",
            receiverAddressId = "14",
            provinceCode = "44000",
            cityCode = "440300",
            countryCode = "44006",
            receiverProvince = "广东省",
            receiverCity = "深圳市",
            receiverCountry = "宝安区",
            receiverArea = "",
            receiverAddress = "沙井中心路28号丽沙花都xx栋xx号",
            receiverPostCode = "",
            receiverLongitude = "113.829127",
            receiverLatitude = "22.713649",
            receiverIdentity = "88888888205468",
            receiverPhone = "17612345678",
            receiverName = "测试用户",
            expectArrivalTime = null,
            senderName = "",
            senderPhone = "",
            senderAddress = "",
            sendTime = null,
            arrivalTime = null
        )
    }

    private fun fetchPaymentView(paymentFee: Int): PaymentView {
        return PaymentView(
            payStatus = 1,
            amount = paymentFee,
            currency = null,
            payType = null,
            payWay = null,
            payWayName = null,
            interactId = null,
            traceNo = null,
            channelTrxNo = null,
            period = null,
            payTime = null,
            paySuccessTime = null
        )
    }

    private fun fetchButtonViews(orderStatus: OrderStatus): List<ButtonView>? {
        return when (orderStatus) {
            OrderStatus.PENDING_PAYMENT -> listOf(
                ButtonView(true, OrderButtonTypes.PAY.code, OrderButtonTypes.PAY.desc),
                ButtonView(false, OrderButtonTypes.CANCEL.code, OrderButtonTypes.CANCEL.desc)
            )

            OrderStatus.PENDING_DELIVERY -> listOf(
                ButtonView(true, OrderButtonTypes.REBUY.code, OrderButtonTypes.REBUY.desc),
                ButtonView(false, OrderButtonTypes.APPLY_REFUND.code, OrderButtonTypes.APPLY_REFUND.desc)
            )

            OrderStatus.PENDING_RECEIPT -> listOf(
                ButtonView(true, OrderButtonTypes.DELIVERY.code, OrderButtonTypes.DELIVERY.desc),
                ButtonView(false, OrderButtonTypes.APPLY_REFUND.code, OrderButtonTypes.APPLY_REFUND.desc)
            )

            OrderStatus.COMPLETE -> listOf(
                ButtonView(true, OrderButtonTypes.COMMENT.code, OrderButtonTypes.COMMENT.desc),
                ButtonView(false, OrderButtonTypes.APPLY_REFUND.code, OrderButtonTypes.APPLY_REFUND.desc)
            )

            OrderStatus.PAYMENT_TIMEOUT -> listOf(
                ButtonView(true, OrderButtonTypes.REBUY.code, OrderButtonTypes.REBUY.desc),
                ButtonView(false, OrderButtonTypes.DELETE.code, OrderButtonTypes.DELETE.desc)
            )

            OrderStatus.CANCELED_NOT_PAYMENT -> listOf(
                ButtonView(true, OrderButtonTypes.REBUY.code, OrderButtonTypes.REBUY.desc),
                ButtonView(false, OrderButtonTypes.DELETE.code, OrderButtonTypes.DELETE.desc)
            )

            OrderStatus.CANCELED_PAYMENT -> listOf(
                ButtonView(true, OrderButtonTypes.REBUY.code, OrderButtonTypes.REBUY.desc),
                ButtonView(false, OrderButtonTypes.DELETE.code, OrderButtonTypes.DELETE.desc)
            )

            OrderStatus.CANCELED_REJECTION -> listOf(
                ButtonView(true, OrderButtonTypes.REBUY.code, OrderButtonTypes.REBUY.desc),
                ButtonView(false, OrderButtonTypes.DELETE.code, OrderButtonTypes.DELETE.desc)
            )
        }
    }

    private fun transSpecifications(specList: List<SpecDetail>): List<Specification> {
        return specList.map { specDetail ->
            Specification.from(specDetail)
        }
    }
}