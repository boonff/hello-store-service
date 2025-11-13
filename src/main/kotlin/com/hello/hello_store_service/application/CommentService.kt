package com.hello.hello_store_service.application

import com.hello.hello_store_service.data.entity.CommentEntity
import com.hello.hello_store_service.data.service.CommentDataService
import com.hello.hello_store_service.web.view.CommentCountView
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentDataService: CommentDataService
) {
    fun fetchByRange(
        spuId: String,
        pageIndex: Int,
        pageSize: Int
    ): List<CommentEntity> {
        val commentsList = fetchCommentsBySpuId(spuId)
        val start = pageSize * pageIndex
        if (start >= commentsList.size) return emptyList() // 页码超出，返回空列表

        val end = (start + pageSize).coerceAtMost(commentsList.size)

        return commentsList.slice(start until end)
    }

    fun fetchDetail(
        spuId: String,
        pageIndex: Int,
        pageSize: Int,
        hasImage: Boolean,
        commentLevel: Int
    ):List<CommentEntity> {
        val commentsList = fetchByRange(spuId, pageIndex, pageSize).toMutableList()

        if (hasImage) commentsList.removeIf { it.commentResources.isEmpty() }
        commentsList.removeIf { it.commentScore <= commentLevel }

        return commentsList
    }

    fun findRandomTop(
        spuId: String,
        randomSize: Int,
        selectSize: Int
    ): List<CommentEntity> {
        val commonsList = fetchCommentsBySpuId(spuId)
        if (commonsList.isEmpty()) return emptyList()

        val randomList = commonsList.shuffled().take(randomSize.coerceAtMost(commonsList.size))

        val sortedList = randomList.sortedBy { it.commentScore }

        val safeSelectSize = selectSize.coerceAtMost(sortedList.size)
        return sortedList.take(safeSelectSize)
    }


    fun getCommentsCount(spuId: String): CommentCountView {
        val commentsList = fetchCommentsBySpuId(spuId)

        val commentCount = commentsList.size
        val goodCount = commentsList.count { it.commentScore >= 4 }
        val middleCount = commentsList.count { it.commentScore == 3 }
        val badCount = commentsList.count { it.commentScore <= 2 }
        val hasImageCount = commentsList.count { it.commentResources.isNotEmpty() }
        val uidCount = commentsList.map { it.uid }.distinct().size //TODO 改为当前用户自己的评论数量

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



    private fun fetchCommentsBySpuId(spuId: String): List<CommentEntity> {
        return commentDataService.fetchSpuComments(spuId)
    }
}