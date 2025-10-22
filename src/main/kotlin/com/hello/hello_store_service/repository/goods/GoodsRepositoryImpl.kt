package com.hello.hello_store_service.repository.goods

import com.hello.hello_store_service.model.entity.goods.SpuEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class SpuRepositoryCustomImpl() : SpuRepositoryCustom {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate
    override fun updateSpu(
        spuId: String,
        spu: SpuEntity
    ): SpuEntity {
        val query = Query(Criteria.where("spuId").`is`(spuId))
        val update = Update()
            .set("title", spu.title)
        mongoTemplate.updateFirst(query, update, SpuEntity::class.java)
        return mongoTemplate.findOne(query, SpuEntity::class.java)!!
    }

    override fun findByRange(
        pageIndex: Int,
        pageSize: Int
    ): List<SpuEntity> {
        val query = Query()
        query.skip((pageIndex * pageSize).toLong())
        query.limit(pageSize)
        return mongoTemplate.find(query, SpuEntity::class.java)
    }

}