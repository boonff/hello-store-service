package com.hello.hello_store_service.utils

import java.time.*

object TimeUtil {

    /** 当前时间戳（毫秒） */
    fun nowMillis(): Long =
        System.currentTimeMillis()

    /** 当前时间戳（秒） */
    fun nowSeconds(): Long =
        System.currentTimeMillis() / 1000

    /** LocalDateTime → 毫秒时间戳 */
    fun toMillis(time: LocalDateTime): Long =
        time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    /** LocalDateTime → 秒时间戳 */
    fun toSeconds(time: LocalDateTime): Long =
        time.atZone(ZoneId.systemDefault()).toInstant().epochSecond

    /** 毫秒时间戳 → LocalDateTime */
    fun fromMillis(timestamp: Long): LocalDateTime =
        Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()

    /** 秒时间戳 → LocalDateTime */
    fun fromSeconds(timestamp: Long): LocalDateTime =
        Instant.ofEpochSecond(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()

    /** 获取 N 分钟后的 LocalDateTime */
    fun minutesAfter(minutes: Long): LocalDateTime =
        LocalDateTime.now().plusMinutes(minutes)

    /** 获取 N 分钟后的毫秒时间戳 */
    fun minutesAfterMillis(minutes: Long): Long =
        toMillis(LocalDateTime.now().plusMinutes(minutes))
}
