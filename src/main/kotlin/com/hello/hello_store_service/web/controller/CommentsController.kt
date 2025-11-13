package com.hello.hello_store_service.web.controller


import com.hello.hello_store_service.application.CommentService
import com.hello.hello_store_service.data.entity.CommentEntity
import com.hello.hello_store_service.data.service.FileDataService
import com.hello.hello_store_service.web.view.CommentCountView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comments")
class CommentsController(
    private val commentsService: CommentService,
    private val fileService: FileDataService
) {
    @GetMapping("/detail")
    fun getRangeComments(
        @RequestParam("spuId") spuId: String,
        @RequestParam("pageIndex") pageIndex: Int,
        @RequestParam("pageSize") pageSize: Int,
        @RequestParam("hasImage") hasImage: Boolean,
        @RequestParam("commentLevel") commentLevel: Int
    ): List<CommentEntity> {
        return commentsService.fetchDetail(spuId, pageIndex, pageSize, hasImage, commentLevel)
    }

    @GetMapping("/range")
    fun getRangeComments(
        @RequestParam("spuId") spuId: String,
        @RequestParam("pageIndex") pageIndex: Int,
        @RequestParam("pageSize") pageSize: Int
    ): List<CommentEntity> {
        return commentsService.fetchByRange(spuId, pageIndex, pageSize)
    }

    @GetMapping("/randomTopComments")
    fun getRandomTopComments(
        @RequestParam("spuId") spuId: String,
        @RequestParam("randomSize") randomSie: Int,
        @RequestParam("selectSize") selectSize: Int
    ): List<CommentEntity> {
        return commentsService.findRandomTop(spuId, randomSie, selectSize)
    }

    @GetMapping("/count")
    fun getCommentCount(@RequestParam("spuId") spuId: String): CommentCountView {
        return commentsService.getCommentsCount(spuId)
    }
}