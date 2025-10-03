package com.hello.hello_store_service.service.store

import com.hello.hello_store_service.model.entity.store.Store
import com.hello.hello_store_service.repository.store.StoreRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class StoreService(private val storeRepository: StoreRepository) {

    // 新增或更新商铺
    fun saveStore(store: Store): Store {
        return storeRepository.save(store)
    }

    // 根据ID查找商铺
    fun getStoreById(id: String): Store? {
        return storeRepository.findById(id).orElse(null)
    }

    fun getStoreByIds(id:List<String>): List<Store> {
        if(id.isEmpty())return emptyList()
        return storeRepository.findByStoreIdIn(id)
    }



    // 删除商铺
    fun deleteStore(storeId: String) {
        storeRepository.deleteById(storeId)
    }

    // 获取所有商铺
    fun getAllStores(): List<Store> {
        return storeRepository.findAll()
    }

    // 根据店铺名称查找
    fun findByName(storeName: String): List<Store> {
        return storeRepository.findByStoreName(storeName)
    }

    // 根据状态查找
    fun findByStatus(storeStatus: Int): List<Store> {
        return storeRepository.findByStoreStatus(storeStatus)
    }

    // 分页查询
    fun getStoresByPage(
        page: Int,
        size: Int,
        sortField: String = "createTime",
        ascending: Boolean = false
    ): Page<Store> {
        val sort = if (ascending) Sort.by(sortField).ascending() else Sort.by(sortField).descending()
        val pageable = PageRequest.of(page, size, sort)
        return storeRepository.findAll(pageable)
    }
}
