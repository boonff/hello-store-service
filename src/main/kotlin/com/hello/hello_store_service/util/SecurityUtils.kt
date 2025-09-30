package com.hello.hello_store_service.util

import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtils {

    fun currentUsername(): String {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw IllegalStateException("No authentication found in context")
        return authentication.name
    }
}
