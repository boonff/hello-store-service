package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.dto.CommentCount
import com.hello.hello_store_service.model.entity.Comments
import com.hello.hello_store_service.service.CommentsService
import com.hello.hello_store_service.service.FileService
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
    fun getAllComments(): List<Comments> {
        return commentsService.findAll()
    }

    @GetMapping("/{spuId}")
    fun getCommentsBySpuId(@PathVariable spuId: String): List<Comments> {
        return commentsService.findBySpuId(spuId)
    }

    @GetMapping("/range")
    fun getRangeComments(
        @RequestParam("spuId") spuId: String,
        @RequestParam("pageIndex") pageIndex: Int,
        @RequestParam("pageSize") pageSize: Int
    ): List<Comments> {
        return commentsService.findByRange(spuId, pageIndex, pageSize)
    }

    @GetMapping("/randomTopComments")
    fun getRandomTopComments(
        @RequestParam("spuId") spuId: String,
        @RequestParam("randomSize") randomSie: Int,
        @RequestParam("selectSize") selectSize: Int
    ): List<Comments> {
        return commentsService.findRandomTop(spuId, randomSie, selectSize)
    }

    @GetMapping("/count")
    fun getCommentCount(@RequestParam("spuId") spuId: String): CommentCount {
        return commentsService.getCommentsCount(spuId)
    }
}