package com.hello.hello_store_service.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("history_search")
data class HistoryEntity(
    @Id val historyId: String,
    @Indexed(unique = true)
    val uid: String,
    val historySearch: List<String>
)