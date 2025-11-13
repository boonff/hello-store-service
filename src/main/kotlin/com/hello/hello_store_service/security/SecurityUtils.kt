package com.hello.hello_store_service.security

import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtils {

    fun fetchUid(): String {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw IllegalStateException("No authentication found in context")
        return authentication.name
    }
}