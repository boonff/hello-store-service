package com.hello.hello_store_service.web.trans.view

data class CommentCountView(
    val commentCount: Int,
    val badCount: Int,
    val middleCount: Int,
    val goodCount: Int,
    val hasImageCount: Int,
    val goodRate: Float,
    val uidCount: Int
)