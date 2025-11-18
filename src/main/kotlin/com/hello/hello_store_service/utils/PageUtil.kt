package com.hello.hello_store_service.utils

object PageUtil {
    /**
     * 泛型分页方法
     * @param list 原始列表
     * @param pageSize 每页数量
     * @param pageIndex 页码，从 1 开始
     * @return 分页后的子列表
     */
    fun <T> sliceList(list: List<T>, pageSize: Int?, pageIndex: Int?): List<T> {
        if (pageSize == null || pageIndex == null) return list

        val start = (pageSize * (pageIndex - 1)).coerceAtLeast(0)
        val end = (start + pageSize).coerceAtMost(list.size)

        return if (start >= list.size) emptyList() else list.subList(start, end)
    }
}
