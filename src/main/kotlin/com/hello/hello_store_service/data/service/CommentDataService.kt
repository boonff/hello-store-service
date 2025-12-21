package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.entity.CommentEntity
import com.hello.hello_store_service.data.repository.CommentsRepository
import org.springframework.stereotype.Service

@Service
class CommentDataService(private val commentsRepository: CommentsRepository) {
        fun fetchSpuComments(spuId: String): List<CommentEntity> {
        return commentsRepository.findBySpuId(spuId)
    }

}