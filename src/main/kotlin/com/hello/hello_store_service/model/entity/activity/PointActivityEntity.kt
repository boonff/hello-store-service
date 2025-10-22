package com.hello.hello_store_service.model.entity.activity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("point_activities")
data class PointActivityEntity(
    @Id
    val id: String? = null,
    val activityId: String? = null,
    val storeId: String,                  // 商铺ID
    val title: String,                     // 活动标题
    val status: Int = 1,                   // 活动状态（Active/Inactive）
    val rule: PointRule,                   // 积分规则
    val startAt: Long,                     // 生效时间
    val endAt: Long,                       // 结束时间
    val productCondition: ProductCondition? = null  // 活动适用商品范围
)

data class PointRule(
    val type: PointRuleType,               // 'Order', 'Signup', 'Promotion'
    val amount: Int,                        // 积分数量或比例
    val maxLimit: Int? = null               // 最大积分限制
)

enum class PointRuleType {
    Order, Signup, Promotion
}
