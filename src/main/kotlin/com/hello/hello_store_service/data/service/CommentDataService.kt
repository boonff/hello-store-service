package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.model.entity.CommentEntity
import com.hello.hello_store_service.data.repository.comment.CommentsRepository
import com.hello.hello_store_service.web.model.view.CommentCountView
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CommentDataService(private val commentsRepository: CommentsRepository) {

    private val logger = LoggerFactory.getLogger(CommentDataService::class.java)

    fun findAll(): List<CommentEntity> {
        return commentsRepository.findAll()
    }

    fun findBySpuId(spuId: String): List<CommentEntity> {
        return commentsRepository.findBySpuId(spuId)
    }

    fun findByRange(
        spuId: String,
        pageIndex: Int,
        pageSize: Int
    ): List<CommentEntity> {
        return commentsRepository.findByRange(spuId, pageIndex, pageSize)
    }

    fun findDetail(
        spuId: String,
        pageIndex: Int,
        pageSize: Int,
        hasImage: Boolean,
        commentLevel: Int
    ): List<CommentEntity> {
        return commentsRepository.findDetail(spuId, pageIndex, pageSize, hasImage, commentLevel)
    }

    fun findRandomTop(
        spuId: String,
        randomSize: Int,
        selectSize: Int
    ): List<CommentEntity> {
        return commentsRepository.findRandomTop(spuId, randomSize, selectSize)
    }

    fun getCommentsCount(spuId: String): CommentCountView {
        val comments = commentsRepository.findBySpuId(spuId)
        logger.info("开始统计评论数据, comments={}", comments)

        val commentCount = comments.size
        val goodCount = comments.count { it.commentScore >= 4 }
        val middleCount = comments.count { it.commentScore == 3 }
        val badCount = comments.count { it.commentScore <= 2 }
        val hasImageCount = comments.count { it.commentResources.isNotEmpty() }
        val uidCount = comments.map { it.uid }.distinct().size //TODO 改为当前用户自己的评论数量

        val goodRate = if (commentCount > 0) {
            goodCount * 100.0 / commentCount
        } else {
            0.0
        }

        return CommentCountView(
            commentCount = commentCount,
            badCount = badCount,
            middleCount = middleCount,
            goodCount = goodCount,
            hasImageCount = hasImageCount,
            goodRate = goodRate.toFloat(),
            uidCount = uidCount
        )
    }

}