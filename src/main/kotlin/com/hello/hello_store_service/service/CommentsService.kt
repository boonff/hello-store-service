package com.hello.hello_store_service.service

import com.hello.hello_store_service.model.dto.CommentCount
import com.hello.hello_store_service.model.entity.Comments

interface CommentsService {
    fun findAll(): List<Comments>
    fun findBySpuId(spuId: String): List<Comments>
    fun findByRange(spuId: String, pageIndex: Int, pageSize: Int): List<Comments>
    fun findRandomTop(spuId: String, randomSize: Int, selectSize: Int): List<Comments>
    fun getCommentsCount(spuId:String): CommentCount
}