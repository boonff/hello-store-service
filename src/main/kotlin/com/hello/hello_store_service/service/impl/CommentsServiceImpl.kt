package com.hello.hello_store_service.service.impl

import com.hello.hello_store_service.model.dto.CommentCount
import com.hello.hello_store_service.model.entity.Comments
import com.hello.hello_store_service.repository.CommentsRepository
import com.hello.hello_store_service.service.CommentsService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CommentsServiceImpl(private val commentsRepository: CommentsRepository) : CommentsService {

    private val logger = LoggerFactory.getLogger(CommentsServiceImpl::class.java)

    override fun findAll(): List<Comments> {
        return commentsRepository.findAll()
    }

    override fun findBySpuId(spuId: String): List<Comments> {
        return commentsRepository.findBySpuId(spuId)
    }

    override fun findByRange(
        spuId: String,
        pageIndex: Int,
        pageSize: Int
    ): List<Comments> {
        return commentsRepository.findByRange(spuId, pageIndex, pageSize)
    }

    override fun findRandomTop(
        spuId: String,
        randomSize: Int,
        selectSize: Int
    ): List<Comments> {
        return commentsRepository.findRandomTopComments(spuId, randomSize, selectSize)
    }

    override fun getCommentsCount(spuId: String): CommentCount {
        val comments = commentsRepository.findBySpuId(spuId)
        logger.info("开始统计评论数据, comments={}", comments)

        val commentCount = comments.size
        val goodCount = comments.count { it.commentScore >= 4 }
        val middleCount = comments.count { it.commentScore == 3 }
        val badCount = comments.count { it.commentScore <= 2 }
        val hasImageCount = comments.count { it.commentResources.isNotEmpty() }
        val uidCount = comments.map { it.uid }.distinct().size

        val goodRate = if (commentCount > 0) {
            goodCount * 100.0 / commentCount
        } else {
            0.0
        }

        return CommentCount(
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