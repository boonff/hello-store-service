package com.hello.hello_store_service.data.service.goods

import com.hello.hello_store_service.data.entity.goods.SpecEntity
import com.hello.hello_store_service.data.entity.goods.SpecValue
import com.hello.hello_store_service.data.repository.goods.SpecRepository

import org.springframework.stereotype.Service

@Service
class SpecDataService(
    private val specRepository: SpecRepository
) {
    fun fetchAll(): List<SpecEntity> = specRepository.findAll()

    fun fetchById(specId: String): SpecEntity? =
        specRepository.findById(specId).orElse(null)

    fun fetchByIds(specIds: List<String>): List<SpecEntity> =
        specRepository.findAllById(specIds)

    fun fetchSpecValue(spec: SpecEntity, valueId: String): SpecValue? {
        return spec.values.firstOrNull { it.specValueId == valueId }
    }

    fun fetchSpecValueBy(specId: String, valueId: String): SpecValue? {
        val spec = fetchById(specId) ?: return null
        return fetchSpecValue(spec, valueId)
    }

    // 查询某商品的所有规格
    fun fetchBySpuId(spuId: String): List<SpecEntity> =
        specRepository.findBySpuId(spuId)

    // 根据名称模糊查询规格
    fun searchByTitle(keyword: String): List<SpecEntity> =
        specRepository.findByTitleContaining(keyword)

    // 新增规格
    fun createSpec(spec: SpecEntity): SpecEntity =
        specRepository.save(spec)

    // 更新规格名称
    fun updateSpecTitle(specId: String, newTitle: String): SpecEntity {
        val spec = specRepository.findById(specId).orElseThrow { RuntimeException("Spec not found") }
        return specRepository.save(spec.copy(title = newTitle))
    }

    // 添加规格值
    fun addSpecValue(specId: String, specValue: SpecValue): SpecEntity {
        val spec = specRepository.findById(specId).orElseThrow { RuntimeException("Spec not found") }
        return specRepository.save(spec.copy(values = spec.values + specValue))
    }

    // 删除规格值
    fun removeSpecValue(specId: String, specValueId: String): SpecEntity {
        val spec = specRepository.findById(specId).orElseThrow { RuntimeException("Spec not found") }
        return specRepository.save(spec.copy(values = spec.values.filter { it.specValueId != specValueId }))
    }

    // 删除整个规格
    fun deleteSpec(specId: String) =
        specRepository.deleteById(specId)
}
