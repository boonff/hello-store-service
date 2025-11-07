package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.model.entity.StoreEntity
import com.hello.hello_store_service.data.repository.StoreRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class StoreDataService(private val storeRepository: StoreRepository) {

    // 新增或更新商铺
    fun saveStore(store: StoreEntity): StoreEntity {
        return storeRepository.save(store)
    }

    // 根据ID查找商铺
    fun fetchStoreById(id: String): StoreEntity? {
        return storeRepository.findById(id).orElse(null)
    }

    fun fetchStoreByIds(id:List<String>): List<StoreEntity> {
        if(id.isEmpty())return emptyList()
        return storeRepository.findByStoreIdIn(id)
    }


    fun deleteStore(storeId: String) {
        storeRepository.deleteById(storeId)
    }

    fun getAllStores(): List<StoreEntity> {
        return storeRepository.findAll()
    }
    fun fetchByName(storeName: String): List<StoreEntity> {
        return storeRepository.findByStoreName(storeName)
    }

    fun fetchByStatus(storeStatus: Int): List<StoreEntity> {
        return storeRepository.findByStoreStatus(storeStatus)
    }

    // 分页查询
    fun getStoresByPage(
        page: Int,
        size: Int,
        sortField: String = "createTime",
        ascending: Boolean = false
    ): Page<StoreEntity> {
        val sort = if (ascending) Sort.by(sortField).ascending() else Sort.by(sortField).descending()
        val pageable = PageRequest.of(page, size, sort)
        return storeRepository.findAll(pageable)
    }
}