package com.hello.hello_store_service.data.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 * 评论实体类，对应 MongoDB 的 "comments" 集合
 */
@Document("comments")
data class CommentEntity(
    @Id
    val id: String? = null,                   // MongoDB 主键 (ObjectId)

    val spuId: String,                        // 商品 SPU ID
    val skuId: String? = null,                // 商品 SKU ID（具体规格）

    val uid: String,                          // 用户 ID
    val userName: String,                     // 用户名
    val userHeadUrl: String? = null,          // 用户头像 URL

    val commentContent: String,               // 评论内容
    val commentScore: Int,                    // 评论分数/星级（1-5）

    val specInfo: String? = null,             // 简单规格信息（如“红色 M 码”）
    val goodsDetailInfo: String? = null,      // 详细规格描述（如“颜色:纯净白 尺码:S码”）

    val isAnonymity: Boolean = false,         // 是否匿名
    val isAutoComment: Boolean = false,       // 是否系统自动生成评论

    val sellerReply: String? = null,          // 商家回复

    val commentTime: Long? = null,            // 评论时间（毫秒时间戳）

    val commentResources: List<CommentResource> = emptyList(), // 评论资源（图片/视频）

    val createdAt: LocalDateTime = LocalDateTime.now(), // 创建时间
    val updatedAt: LocalDateTime = LocalDateTime.now()  // 最后更新时间
)

/**
 * 评论附带的资源（图片/视频）
 */
data class CommentResource(
    val src: String,                          // 资源地址（图片/视频 URL）
    val type: String,                         // 资源类型：image / video
    val coverSrc: String? = null              // 视频封面（仅视频时使用）
)
