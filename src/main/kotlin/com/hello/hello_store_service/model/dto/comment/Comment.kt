package com.hello.hello_store_service.model.dto.comment

data class CommentCount(
    val commentCount: Int,
    val badCount: Int,
    val middleCount: Int,
    val goodCount: Int,
    val hasImageCount: Int,
    val goodRate: Float,
    val uidCount: Int
)