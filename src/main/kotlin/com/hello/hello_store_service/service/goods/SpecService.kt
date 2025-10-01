package com.hello.hello_store_service.service.goods

import com.hello.hello_store_service.model.entity.goods.Spec
import com.hello.hello_store_service.model.entity.goods.SpecValue
import com.hello.hello_store_service.repository.goods.SpecRepository
import org.springframework.stereotype.Service

@Service
class SpecService(
    private val specRepository: SpecRepository
) {

    // 查询某商品的所有规格
    fun getSpecsBySpuId(spuId: String): List<Spec> =
        specRepository.findBySpuId(spuId)

    // 根据名称模糊查询规格
    fun searchSpecsByTitle(keyword: String): List<Spec> =
        specRepository.findByTitleContaining(keyword)

    // 新增规格
    fun createSpec(spec: Spec): Spec =
        specRepository.save(spec)

    // 更新规格名称
    fun updateSpecTitle(specId: String, newTitle: String): Spec {
        val spec = specRepository.findById(specId).orElseThrow { RuntimeException("Spec not found") }
        return specRepository.save(spec.copy(title = newTitle))
    }

    // 添加规格值
    fun addSpecValue(specId: String, specValue: SpecValue): Spec {
        val spec = specRepository.findById(specId).orElseThrow { RuntimeException("Spec not found") }
        return specRepository.save(spec.copy(values = spec.values + specValue))
    }

    // 删除规格值
    fun removeSpecValue(specId: String, specValueId: String): Spec {
        val spec = specRepository.findById(specId).orElseThrow { RuntimeException("Spec not found") }
        return specRepository.save(spec.copy(values = spec.values.filter { it.specValueId != specValueId }))
    }

    // 删除整个规格
    fun deleteSpec(specId: String) =
        specRepository.deleteById(specId)
}
