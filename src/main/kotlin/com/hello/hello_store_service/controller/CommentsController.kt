package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.view.comment.CommentCountView
import com.hello.hello_store_service.model.entity.comment.CommentEntity
import com.hello.hello_store_service.service.comment.CommentsService
import com.hello.hello_store_service.service.file.FileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comments")
class CommentsController(
    private val commentsService: CommentsService,
    private val fileService: FileService
) {
    @GetMapping()
    fun getAllComments(): List<CommentEntity> {
        return commentsService.findAll()
    }

    @GetMapping("/{spuId}")
    fun getCommentsBySpuId(@PathVariable spuId: String): List<CommentEntity> {
        return commentsService.findBySpuId(spuId)
    }

    @GetMapping("/detail")
    fun getRangeComments(
        @RequestParam("spuId") spuId: String,
        @RequestParam("pageIndex") pageIndex: Int,
        @RequestParam("pageSize") pageSize: Int,
        @RequestParam("hasImage") hasImage: Boolean,
        @RequestParam("commentLevel") commentLevel: Int
    ): List<CommentEntity> {
        return commentsService.findDetail(spuId, pageIndex, pageSize, hasImage, commentLevel)
    }

    @GetMapping("/range")
    fun getRangeComments(
        @RequestParam("spuId") spuId: String,
        @RequestParam("pageIndex") pageIndex: Int,
        @RequestParam("pageSize") pageSize: Int
    ): List<CommentEntity> {
        return commentsService.findByRange(spuId, pageIndex, pageSize)
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